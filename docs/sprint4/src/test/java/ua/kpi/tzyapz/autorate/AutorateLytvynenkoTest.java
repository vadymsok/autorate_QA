package ua.kpi.tzyapz.autorate;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("АвтоРейт — юніт-тести бізнес-логіки (Литвиненко А)")
class AutorateLytvynenkoTest {

    private RatingAggregator aggregator;
    private VoteValidator validator;
    private ModelSearch search;

    @BeforeEach
    void setUp() {
        aggregator = new RatingAggregator();
        validator = new VoteValidator();
        search = new ModelSearch();
    }

    @Test
    @DisplayName("BL-10: порожній список голосів дає 0.0, а некоректні бали/репутація кидають IllegalArgumentException")
    void averageEmptyAndInvalidInputs() {
        // Arrange
        Vote invalidRating = new Vote("u1", "m1", 6, 1, "ok");
        Vote invalidReputation = new Vote("u1", "m1", 4, 0, "ok");

        // Act & Assert
        assertEquals(0.0, aggregator.average(List.of()), 1e-9);
        assertThrows(IllegalArgumentException.class, () -> aggregator.average(List.of(invalidRating)));
        assertThrows(IllegalArgumentException.class, () -> aggregator.average(List.of(invalidReputation)));
    }

    @Test
    @DisplayName("BL-20: topN повертає порожній список при n <= 0 і відфільтровує моделі з голосами < MIN_VOTES")
    void topNRespectsNonPositiveLimitAndMinVotes() {
        // Arrange
        List<CarModel> models = List.of(
                new CarModel("Porsche", "911", 4.9, RatingAggregator.MIN_VOTES),
                new CarModel("Audi", "R8", 5.0, Math.max(0, RatingAggregator.MIN_VOTES - 1))
        );

        // Act
        List<CarModel> zeroResult = aggregator.topN(models, 0);
        List<CarModel> negativeResult = aggregator.topN(models, -2);
        List<CarModel> validTop = aggregator.topN(models, 5);

        assertAll(
                () -> assertTrue(zeroResult.isEmpty()),
                () -> assertTrue(negativeResult.isEmpty()),
                () -> assertEquals(1, validTop.size()),
                () -> assertEquals("Porsche 911", validTop.get(0).fullName())
        );
    }

    @Test
    @DisplayName("BL-30 / BL-50: повторний голос та коментар > 200 символів фіксуються у списку помилок")
    void duplicateVoteAndLongCommentValidation() {

        Vote previousVote = new Vote("user-1", "model-10", 4, 2, "добре");
        String tooLongComment = "a".repeat(201);

        List<String> errors = validator.validate(
                "user-1",
                "model-10",
                5,
                tooLongComment,
                List.of(previousVote)
        );

        assertAll(
                () -> assertTrue(errors.contains("повторний голос")),
                () -> assertTrue(errors.contains("коментар задовгий"))
        );
    }

    @Test
    @DisplayName("BL-70: sortByRating повертає новий відсортований список і не змінює вхідний список")
    void sortByRatingDoesNotMutateInputList() {

        CarModel low = new CarModel("Skoda", "Octavia", 3.8, 10);
        CarModel high = new CarModel("BMW", "M3", 4.8, 25);
        List<CarModel> original = new ArrayList<>(List.of(low, high));

        List<CarModel> sorted = search.sortByRating(original);

        assertAll(
                () -> assertNotSame(original, sorted),
                () -> assertEquals(List.of(low, high), original),
                () -> assertEquals("M3", sorted.get(0).name())
        );
    }

    @ParameterizedTest(name = "BL-80: стоп-слово \"{0}\" виявляється незалежно від регістру")
    @CsvSource({
            "SPAM",
            "Scam",
            "КАЗИНО",
            "CaSiNo"
    })
    @DisplayName("BL-80: перевірка стоп-слів у коментарі та виняток при null у конструкторі")
    void stopWordsDetectionAndNullCollectionException(String word) {
        assertTrue(validator.containsStopWord("Це точно " + word + " лінк"));
        assertThrows(IllegalArgumentException.class, () -> new VoteValidator(null));
    }
}
