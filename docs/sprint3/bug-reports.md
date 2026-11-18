# Реєстр дефектів demo

30 TC; **7 Passed / 9 Failed / 14 Blocked**; **10 чинних BUG**, 3 Deferred; 4 рівні Severity. Незалежне review: **0/30 TC, 0/10 BUG**. DoD повністю не виконано.

Повний локальний трекер за §4.15. New означає створений звіт, а не виправлення/Verified. Рецензент і дата review заповнюються лише реальною людиною після перевірки.

| BUG | Severity | Priority | Linked TC | SRS |
|---|---|---|---|---|
| [BUG-101](#bug-101) | Major | P3 | TC-007 | F-110 |
| [BUG-103](#bug-103) | Normal | P3 | TC-007 | GUI-10 |
| [BUG-105](#bug-105) | Trivial | P3 | TC-013 | GUI-60 |
| [BUG-106](#bug-106) | Minor | P3 | TC-026 | GUI-60, F-120 |
| [BUG-107](#bug-107) | Minor | P3 | TC-023 | GUI-45 |
| [BUG-108](#bug-108) | Minor | P3 | TC-011 | GUI-70 |
| [BUG-109](#bug-109) | Trivial | P3 | TC-028 | GUI-40, U-20 |
| [BUG-111](#bug-111) | Normal | P3 | TC-008 | F-80, F-90 |
| [BUG-112](#bug-112) | Major | P3 | TC-020 | F-130 |
| [BUG-113](#bug-113) | Major | P3 | TC-030 | F-90, GUI-80 |

## BUG-101
**Title:** Картка Diablo не містить повного набору окремих полів із F-110
**Severity:** Major · **Priority:** P3
**Linked Test Case:** TC-007 · **Requirement:** F-110
**Reporter:** Журнал браузерного тестування (Run 1–4) · **Date:** 2026-11-16 · **Status:** New
**Reviewer:** — · **Review date:** —

**Description**
Користувач не може порівняти автомобіль за всіма потрібними характеристиками. Major: важлива функція каталогу реалізована неповно. P3: перегляд сторінок і подальше виконання доступних кейсів не блокуються.

**Preconditions**
Chrome із JavaScript; публічний каталог завантажується, дані змінні; гостьова сесія для каталогу. Конкретні умови наведені у кроках і Actual.

**Steps to reproduce**

1. Відкрити https://buggy.justtestit.org/ у новій гостьовій вкладці Chrome.
2. Натиснути зображення Popular Model (у зафіксованій версії Lamborghini Diablo).
3. Дочекатися Specification.
4. Переглянути окремі поля картки й Specification.

**Expected Result**
F-110 вимагає повний перелік полів картки: Марка, Модель, Рік випуску, Тип кузова, Об’єм двигуна, Тип палива, Трансмісія, Потужність, Середній рейтинг, Кількість голосів. Усі ці поля мають бути доступні як дані картки.

**Actual Result**
На картці є марка Lamborghini, модель Diablo, фото, Votes. У Specification лише Engine: 6.0l і Max Speed: 25km/h. Окремих полів року випуску, кузова, палива, трансмісії та середнього рейтингу немає. Потужність і роки згадані всередині опису, але не утворюють повного набору полів картки.

**Environment**
https://buggy.justtestit.org/; первинний прогін 2026-11-16. Для історичних звітів ENV-01/03 з archive-run3, повторна перевірка 17.11: ENV-04 — CloudBrowser Chrome/CDP, desktop 1363 CSS px; 17.11.2026; власна тестова сесія vasya1 підготовлена користувачем захищеним входом; точні версії Chrome/OS не отримано. Роль: гість або власна сесія для публічного каталогу; BUG-109 — гостьова Register.

**Screenshot / Attachment**
Specification і Votes видно; на знімку відсутній повний набір полів. Це не твердження про відсутність поля введення rating із F-50.

- [BUG-101-1.jpg](attachments/BUG-101-1.jpg)
- [BUG-101-103-111-retest.jpg](attachments/BUG-101-103-111-retest.jpg)

**Retest 17.11:** Відтворено частину картки: Specification має лише Engine/Max Speed; повних окремих полів F-110 немає.

## BUG-103
**Title:** Сторінка Diablo розтягується до 5428 px на desktop viewport 1348 px
**Severity:** Normal · **Priority:** P3
**Linked Test Case:** TC-007 · **Requirement:** GUI-10
**Reporter:** Журнал браузерного тестування (Run 1–4) · **Date:** 2026-11-16 · **Status:** New
**Reviewer:** — · **Review date:** —

**Description**
Після відображення наявної таблиці коментарів виникає небажана горизонтальна прокрутка. Normal: сторінкою можна користуватися, але читання вимагає додаткової прокрутки. P3: інші сценарії не заблоковано. Критерій відсутності такого скролу задано також у тест-плані Спринту 2 (§2, GUI).

**Preconditions**
Chrome із JavaScript; публічний каталог завантажується, дані змінні; гостьова сесія для каталогу. Конкретні умови наведені у кроках і Actual.

**Steps to reproduce**

1. Відкрити https://buggy.justtestit.org/ у новій гостьовій вкладці Chrome з desktop viewport приблизно 1348×921 CSS px.
2. Натиснути Popular Model (Diablo).
3. Дочекатися Specification і таблиці наявних коментарів.
4. Перевірити горизонтальну смугу прокрутки внизу вікна.

**Expected Result**
Адаптивний інтерфейс за GUI-10 і критерій GUI тест-плану §2 не повинні мати небажаного горизонтального скролу на desktop. Таблиця/довгі рядки мають поміщатися в область вмісту або мати локальне кероване прокручування.

**Actual Result**
Ширина viewport 1348 px, документа 5428 px, таблиці 5324,20 px. Видима горизонтальна смуга прокрутки, вміст виходить за межі desktop. На момент спостереження в DOM таблиці було 3699 рядків спільних коментарів; власні довгі коментарі не додавалися.

**Environment**
https://buggy.justtestit.org/; первинний прогін 2026-11-16. Для історичних звітів ENV-01/03 з archive-run3, повторна перевірка 17.11: ENV-04 — CloudBrowser Chrome/CDP, desktop 1363 CSS px; 17.11.2026; власна тестова сесія vasya1 підготовлена користувачем захищеним входом; точні версії Chrome/OS не отримано. Роль: гість або власна сесія для публічного каталогу; BUG-109 — гостьова Register.

**Screenshot / Attachment**
Внизу видно горизонтальну смугу; JSON містить розміри DOM. Коренева причина CSS не встановлювалася. Потрібна наявна довга таблиця коментарів, дані публічного стенда змінні.

- [BUG-103-1.jpg](attachments/BUG-103-1.jpg)
- [BUG-103-layout.json](attachments/BUG-103-layout.json)
- [BUG-101-103-111-retest.jpg](attachments/BUG-101-103-111-retest.jpg)
- [model-retest.json](attachments/model-retest.json)

**Retest 17.11:** Відтворено: viewport=1363, document=5428, table≈5324.20 CSS px; rows=3699. Старі розміри 1348 лишаються історичним фактом.

## BUG-105
**Title:** Пагінація Overall Rating вирівняна праворуч замість центру під таблицею
**Severity:** Trivial · **Priority:** P3
**Linked Test Case:** TC-013 · **Requirement:** GUI-60
**Reporter:** Журнал браузерного тестування (Run 1–4) · **Date:** 2026-11-16 · **Status:** New
**Reviewer:** — · **Review date:** —

**Description**
Положення елементів не відповідає явному правилу GUI-60. Trivial: косметичне вирівнювання, сторінки перемикаються. P3: дефект не блокує жоден інший кейс. Колізія кількості 5/10 не впливає на окремий критерій центрування.

**Preconditions**
Chrome із JavaScript; публічний каталог завантажується, дані змінні; гостьова сесія для каталогу. Конкретні умови наведені у кроках і Actual.

**Steps to reproduce**

1. Відкрити https://buggy.justtestit.org/ у новій гостьовій вкладці Chrome.
2. Натиснути Overall Rating.
3. Дочекатися повної першої сторінки.
4. Прокрутити до нижнього краю таблиці.
5. Перевірити положення «, поля номера сторінки, » і page 1 of 5.

**Expected Result**
GUI-60 прямо вимагає елементи навігації по центру під таблицею. Блок має бути центрований відносно таблиці, а не притиснутий до правого краю.

**Actual Result**
Пагінація праворуч. Контейнер таблиці/рядка x=104..1244 CSS px, центр 674; блок пагінації x=1024,77..1244, центр приблизно 1134,38. DOM-клас блока — pull-xs-right.

**Environment**
https://buggy.justtestit.org/; первинний прогін 2026-11-16. Для історичних звітів ENV-01/03 з archive-run3, повторна перевірка 17.11: ENV-04 — CloudBrowser Chrome/CDP, desktop 1363 CSS px; 17.11.2026; власна тестова сесія vasya1 підготовлена користувачем захищеним входом; точні версії Chrome/OS не отримано. Роль: гість або власна сесія для публічного каталогу; BUG-109 — гостьова Register.

**Screenshot / Attachment**
Повна таблиця й пагінація внизу праворуч; JSON із геометрією. Це не BUG про суперечливі 5/10 записів.

- [BUG-105-1.jpg](attachments/BUG-105-1.jpg)
- [BUG-105-layout.json](attachments/BUG-105-layout.json)
- [BUG-105-retest.jpg](attachments/BUG-105-retest.jpg)
- [BUG-105-retest.json](attachments/BUG-105-retest.json)

**Retest 17.11:** Відтворено й виміряно 17.11: таблиця x=104, width=1140, центр=674; пагінація x=1024.765625, width=219.234375, центр≈1134.38. Контейнер pull-xs-right, розміщення праворуч.

## BUG-106
**Title:** Каталог приймає номер 6 за наявності 5 сторінок і показує порожній список
**Severity:** Minor · **Priority:** P3
**Linked Test Case:** TC-026 · **Requirement:** GUI-60, F-120
**Reporter:** Журнал браузерного тестування (Run 1–4) · **Date:** 2026-11-16 · **Status:** New
**Reviewer:** — · **Review date:** —

**Description**
Minor: невірний ввід номера порушує навігацію каталогу, але перезавантаження відновлює роботу. P3: інші тести доступні. Очікування — логічна цілісність індикатора 1..N, дозволена як основа Expected за §4.11; це не дослівна межа SRS.

**Preconditions**
Chrome із JavaScript; публічний каталог завантажується, дані змінні; гостьова сесія для каталогу. Конкретні умови наведені у кроках і Actual.

**Steps to reproduce**

1. Відкрити https://buggy.justtestit.org/.
2. Натиснути Overall Rating.
3. Дочекатися моделей і записати N сторінок.
4. У поле номера сторінки ввести N+1 (для цього прогону 6).
5. Натиснути Enter і перевести фокус на заголовок.
6. Після завершення завантаження перевірити індикатор сторінки й список.
7. Спробувати повернути номер 1 (Enter/blur); у прогоні після невдачі перевірено також 0.
8. Якщо список не відновився, перезавантажити вкладку один раз.

**Expected Result**
Некоректний номер не повинен переводити каталог у суперечливий стан page 6 of 5. Очікується відхилення або нормалізація в межі 1..N зі збереженням доступного каталогу. Це критерій логічної цілісності пагінації GUI-60 / доступності F-120, а не дослівний діапазон із SRS.

**Actual Result**
За N=5 введено 6: page 6 of 5, таблиця має 0 моделей після завантаження. Спроба 1/Enter/blur не повернула список; перезавантаження відновило Diablo та першу сторінку. Початкова окрема проба 0 на сторінці 1 залишила page 1.

**Environment**
https://buggy.justtestit.org/; первинний прогін 2026-11-16. Для історичних звітів ENV-01/03 з archive-run3, повторна перевірка 17.11: ENV-04 — CloudBrowser Chrome/CDP, desktop 1363 CSS px; 17.11.2026; власна тестова сесія vasya1 підготовлена користувачем захищеним входом; точні версії Chrome/OS не отримано. Роль: гість або власна сесія для публічного каталогу; BUG-109 — гостьова Register.

**Screenshot / Attachment**
BUG-106-1: page 6 of 5 і порожня таблиця; BUG-106-2: після спроб повернення 1/0; TC-026-recovered: після reload.

- [BUG-106-1.jpg](attachments/BUG-106-1.jpg)
- [BUG-106-2.jpg](attachments/BUG-106-2.jpg)
- [TC-026-recovered.jpg](attachments/TC-026-recovered.jpg)
- [BUG-106-retest.jpg](attachments/BUG-106-retest.jpg)
- [BUG-106-retest.json](attachments/BUG-106-retest.json)

**Retest 17.11:** Відтворено: page 6 of 5, rows=0. Ввід 1/Enter/blur повертає 6. Коректний обхід: головна → Overall Rating відновив page1. Діапазон — логічний оракул, не дослівний текст SRS.

## BUG-107
**Title:** Save активна на щойно відкритому Profile без змін
**Severity:** Minor · **Priority:** P3
**Linked Test Case:** TC-023 · **Requirement:** GUI-45
**Reporter:** Журнал браузерного тестування (Run 1–4) · **Date:** 2026-11-16 · **Status:** New
**Reviewer:** — · **Review date:** —

**Description**
Minor: порушення стану кнопки дозволяє непотрібне збереження; редагування доступне. P3: інші кейси не блокує.

**Preconditions**
Власна чинна SESSION-B3; Profile ще не редагувався після відкриття.

**Steps to reproduce**

1. Відкрити https://buggy.justtestit.org/.
2. У власній сесії натиснути Profile.
3. Дочекатися полів, нічого не вводити.
4. Прокрутити до Save та перевірити доступність.
5. Для повторення вийти через Cancel і знову відкрити Profile.

**Expected Result**
GUI-45: Save неактивна без змін у профілі.

**Actual Result**
Save активна відразу після завантаження; isEnabled=true. Повторне відкриття відтворює стан. Після фактичного Save кнопка стає disabled, але нове відкриття знову активує її.

**Environment**
https://buggy.justtestit.org/; первинний прогін 2026-11-16. Для історичних звітів ENV-01/03 з archive-run3, повторна перевірка 17.11: ENV-04 — CloudBrowser Chrome/CDP, desktop 1363 CSS px; 17.11.2026; власна тестова сесія vasya1 підготовлена користувачем захищеним входом; точні версії Chrome/OS не отримано. Роль: власний зареєстрований тестовий користувач.

**Screenshot / Attachment**
Головний знімок зроблено після нового відкриття, до будь-яких змін цього відкриття.

- [BUG-107-unchanged-save.jpg](attachments/BUG-107-unchanged-save.jpg)
- [TC-023-initial.jpg](attachments/TC-023-initial.jpg)
- [BUG-107-retest.json](attachments/BUG-107-retest.json)

**Retest 17.11:** Відтворено булевим контролем: нове відкриття Profile, без редагування Save enabled=true. Первинний знімок Save збережено; новий знімок завантаження не використовується як доказ кнопки.

## BUG-108
**Title:** First Name у Profile не має пояснювальної підказки
**Severity:** Minor · **Priority:** P3
**Linked Test Case:** TC-011 · **Requirement:** GUI-70
**Reporter:** Журнал браузерного тестування (Run 1–4) · **Date:** 2026-11-16 · **Status:** New
**Reviewer:** — · **Review date:** —

**Description**
Minor: відсутня передбачена допомога; введення працює. P3: доступні інші перевірки.

**Preconditions**
Власна чинна SESSION-B3; Profile завантажений.

**Steps to reproduce**

1. Відкрити https://buggy.justtestit.org/.
2. У власній сесії відкрити Profile.
3. Сфокусувати First Name без редагування.
4. Навести вказівник на First Name та перевірити пояснення.
5. Порівняти назву поля, допоміжний текст і tooltip.

**Expected Result**
GUI-70: форма профілю має підказки для всіх полів вводу; сам label не є пояснювальною підказкою.

**Actual Result**
Є лише label First Name; пояснювальної підказки при фокусі/наведенні немає. Для input firstName title, placeholder, aria-describedby відсутні.

**Environment**
https://buggy.justtestit.org/; первинний прогін 2026-11-16. Для історичних звітів ENV-01/03 з archive-run3, повторна перевірка 17.11: ENV-04 — CloudBrowser Chrome/CDP, desktop 1363 CSS px; 17.11.2026; власна тестова сесія vasya1 підготовлена користувачем захищеним входом; точні версії Chrome/OS не отримано. Роль: власний зареєстрований тестовий користувач.

**Screenshot / Attachment**
Знімок з фокусом/вказівником. JSON містить лише несекретні атрибути, не значення паролів. Для доведення порушення «всі» наведено один конкретний counterexample.

- [BUG-108-no-help.jpg](attachments/BUG-108-no-help.jpg)
- [BUG-108-help-attributes.json](attachments/BUG-108-help-attributes.json)
- [BUG-108-retest.jpg](attachments/BUG-108-retest.jpg)
- [BUG-108-retest.json](attachments/BUG-108-retest.json)

**Retest 17.11:** Повторно фокус First Name; title/placeholder/aria-describedby=null, пояснення немає. Наведення 17.11 повторно не виконувалось; історичний hover збережено.

## BUG-109
**Title:** Помилка порожнього Last Name неправильно називає First Name
**Severity:** Trivial · **Priority:** P3
**Linked Test Case:** TC-028 · **Requirement:** GUI-40, U-20
**Reporter:** Журнал браузерного тестування (Run 1–4) · **Date:** 2026-11-16 · **Status:** New
**Reviewer:** — · **Review date:** —

**Description**
Trivial: неточний текст поля; required і блокування Register працюють. P3: інші тести не блокуються.

**Preconditions**
Гість. У Register секретів не вводити; First Name=Test, Last Name ще не заповнено.

**Steps to reproduce**

1. Відкрити https://buggy.justtestit.org/.
2. Натиснути Register.
3. Ввести Test у First Name.
4. Ввести QA у Last Name і повністю очистити його.
5. Перевести фокус Tab із Last Name.
6. Прочитати помилку під Last Name.

**Expected Result**
Помилка має ідентифікувати Last Name. GUI-40 задає обов’язковість поля, U-20 — текстове повідомлення. Відповідність назви поля — логічна цілісність за §4.11, не дослівно задана англійська фраза SRS.

**Actual Result**
First Name валідне (Test, зелена межа), Last Name порожнє з червоною межею, але під ним написано First Name is required. Повторено в гостьовій формі.

**Environment**
https://buggy.justtestit.org/; первинний прогін 2026-11-16. Для історичних звітів ENV-01/03 з archive-run3, повторна перевірка 17.11: ENV-04 — CloudBrowser Chrome/CDP, desktop 1363 CSS px; 17.11.2026; власна тестова сесія vasya1 підготовлена користувачем захищеним входом; точні версії Chrome/OS не отримано. Роль: гість або власна сесія для публічного каталогу; BUG-109 — гостьова Register.

**Screenshot / Attachment**
Скриншот одночасно показує Test у First Name і неправильний текст під Last Name. Серверне прийняття порожнього поля не заявляється.

- [BUG-109-guest-reproduced.jpg](attachments/BUG-109-guest-reproduced.jpg)
- [BUG-109-wrong-field-message.jpg](attachments/BUG-109-wrong-field-message.jpg)
- [BUG-109-register-retest.jpg](attachments/BUG-109-register-retest.jpg)
- [BUG-109-register-retest.txt](attachments/BUG-109-register-retest.txt)

**Retest 17.11:** Відтворено саме Register: First Name=Test, Last Name очищено, під Last Name текст First Name is required. Контроль у Profile показав коректне Last Name is required; не змішувати форми.

## BUG-111
**Title:** Коментар прийнятого голосу за Diablo не з’являється у таблиці після повторного відкриття
**Severity:** Normal · **Priority:** P3
**Linked Test Case:** TC-008 · **Requirement:** F-80, F-90
**Reporter:** Журнал браузерного тестування (Run 1–4) · **Date:** 2026-11-16 · **Status:** New
**Reviewer:** — · **Review date:** —

**Description**
Normal: лічильник і підтвердження голосу працюють, але власний відгук у публічній таблиці недоступний. P3: виконання інших кейсів не блокується.

**Preconditions**
Власний акаунт, який ще не голосував за Diablo; форма Your Comment (optional)/Vote! доступна. Не повторювати голос із уже використаного акаунта.

**Steps to reproduce**

1. Відкрити https://buggy.justtestit.org/.
2. Увійти у власний свіжий тестовий акаунт вручну.
3. Натиснути Popular Model (Diablo в цьому прогоні).
4. Ввести у Comment унікальний текст QA team B - vote test TC008 20261116-auth (для нового прогону використати інший маркер).
5. Натиснути Vote! один раз і дочекатися завершення запиту.
6. Перевірити Thank you for your vote! та заміну форми.
7. Переглянути таблицю коментарів на наявність власного маркера.
8. Відкрити марку Lamborghini, знову Diablo та повторно перевірити таблицю.

**Expected Result**
Прийнятий голос із непорожнім коментарем має зробити цей коментар доступним у таблиці моделі після повторного відкриття. Основа — F-80 (таблиця коментарів), F-90 (голос із коментарем) та логічна цілісність §4.11; точний строк оновлення/зберігання SRS не задає. Серверне видалення або коренева причина не стверджуються. Повторна перевірка наступного дня посилює спостереження; строк публікації лишається відкритим питанням, але §4.11 дозволяє явно позначений логічний оракул.

**Actual Result**
Thank you for your vote!; Votes 11067→11068. Власний маркер не з’явився відразу та після повторних відкриттів. Read-only перевірка всього DOM таблиці: rows=3699, hasOwn=false, matchingRows=[]. Для контрольного TC-009 на Reventón власний інший коментар був одразу видимий.

**Environment**
https://buggy.justtestit.org/; первинний прогін 2026-11-16. Для історичних звітів ENV-01/03 з archive-run3, повторна перевірка 17.11: ENV-04 — CloudBrowser Chrome/CDP, desktop 1363 CSS px; 17.11.2026; власна тестова сесія vasya1 підготовлена користувачем захищеним входом; точні версії Chrome/OS не отримано. Роль: власний зареєстрований тестовий користувач.

**Screenshot / Attachment**
Знімок сам по собі не доводить відсутність у всіх рядках: додано вузьку DOM-перевірку за унікальним маркером. Повторне відтворення потребує свіжого власного акаунта, оскільки попередній голос не скасувати через UI.

- [TC-008-vote-result.jpg](attachments/TC-008-vote-result.jpg)
- [BUG-111-comment-missing.jpg](attachments/BUG-111-comment-missing.jpg)
- [TC-008-comment-check.json](attachments/TC-008-comment-check.json)
- [TC-009-first-vote.jpg](attachments/TC-009-first-vote.jpg)
- [BUG-101-103-111-retest.jpg](attachments/BUG-101-103-111-retest.jpg)
- [model-retest.json](attachments/model-retest.json)

**Retest 17.11:** Симптом повторно спостерігається наступного дня: маркер TC008 20261116-auth досі відсутній у всій DOM-таблиці Diablo (3699 рядків), власний голос уже прийнято. Новий submit на Diablo не робився; кореневу причину не встановлено.

## BUG-112
**Title:** З головної та каталогу немає доступного тижневого лідерборду за F-130
**Severity:** Major · **Priority:** P3
**Linked Test Case:** TC-020 · **Requirement:** F-130
**Reporter:** Журнал браузерного тестування (Run 1–4) · **Date:** 2026-11-17 · **Status:** New
**Reviewer:** — · **Review date:** —

**Description**
Major: передбачений F-130 тижневий відбір автомобілів недоступний користувачу через оглянуті екрани. P3: доступні інші кейси; порогові набори цього самого сценарію не можна продовжити, але його наявність уже Failed. Оракул: F-130 розміщено у §3 функціональних вимог, а §2 методички задає demo як поточний продукт і SRS як еталон; це інтерпретація чинної базової версії, не погодження викладача про реліз.

**Preconditions**
Chrome/JavaScript; гість або власна авторизована тестова сесія. Знання прихованих маршрутів не потрібне; перевіряється доступність для звичайного користувача.

**Steps to reproduce**

1. Відкрити https://buggy.justtestit.org/.
2. Дочекатися трьох основних блоків.
3. Оглянути доступні переходи й розділи головної.
4. Натиснути блок Overall Rating.
5. Дочекатися таблиці й оглянути доступні переходи/перемикачі типу рейтингу.
6. Спробувати відкрити тижневий лідерборд із цих засобів навігації.

**Expected Result**
За F-130 користувачу має бути доступне тижневе подання з відбором n≥5. Тут перевіряється необхідна передумова цього правила — доступна функція. Відсутність обов’язкової функції на demo є невідповідністю за §2 методички. Якщо замовник офіційно віднесе F-130 лише до майбутнього релізу, звіт підлягає Deferred; такого рішення в архіві немає.

**Actual Result**
На головній Popular Make, Popular Model, Overall Rating. Каталог показує загальні Votes/Rank і пагінацію; доступного weekly leaderboard або перемикача/переходу до нього немає. Порогові 4/5 не перевірені. Не робиться висновку про всі невідомі маршрути сервера.

**Environment**
https://buggy.justtestit.org/; первинний прогін 2026-11-17. Для історичних звітів ENV-01/03 з archive-run3, повторна перевірка 17.11: ENV-04 — CloudBrowser Chrome/CDP, desktop 1363 CSS px; 17.11.2026; власна тестова сесія vasya1 підготовлена користувачем захищеним входом; точні версії Chrome/OS не отримано. Роль: гість або власна сесія для публічного каталогу; BUG-109 — гостьова Register.

**Screenshot / Attachment**
Домашні блоки/посилання та каталог зафіксовано знімками й вузьким DOM-аудитом видимої навігації; це доказ відсутності доступного переходу, а не сканування маршрутів.

- [BUG-112-home.jpg](attachments/BUG-112-home.jpg)
- [BUG-112-home.json](attachments/BUG-112-home.json)
- [BUG-105-112-overall.jpg](attachments/BUG-105-112-overall.jpg)
- [overall-retest.json](attachments/overall-retest.json)

**Retest 17.11:** Новий дефект доступності функції; F-130 у §3 + §2 методички, порогові піднабори залишилися неперевіреними.

## BUG-113
**Title:** Порожній Comment дозволяє успішно зарахувати голос за Veneno
**Severity:** Major · **Priority:** P3
**Linked Test Case:** TC-030 · **Requirement:** F-90, GUI-80
**Reporter:** Журнал браузерного тестування (Run 1–4) · **Date:** 2026-11-17 · **Status:** New
**Reviewer:** — · **Review date:** —

**Description**
Major: важливе правило прийняття голосу з коментарем порушено; порожній ввід реально приводить до зарахування. P3: інші сценарії доступні. F-90 §3 описує вебзастосунок; BL-50 §5 стосується Java-модуля і дозволяє ширший набір на внутрішньому рівні, що не скасовує строгішу вебвалідацію.

**Preconditions**
Власна авторизована тестова сесія, яка ще не голосувала за обрану модель. У прогоні SESSION-B4/vasya1, Veneno; Comment/Vote! видимі, Comment порожній. Для нового повторення взяти іншу непроголосовану модель або новий власний акаунт; не використовувати чужі.

**Steps to reproduce**

1. Відкрити https://buggy.justtestit.org/.
2. Увійти у власний тестовий акаунт через захищене введення.
3. Відкрити Popular Make → Lamborghini → Veneno (або зафіксовану непроголосовану модель).
4. Перевірити доступну форму й Comment довжини 0; зафіксувати початковий Votes.
5. Натиснути Vote! один раз, не вводячи коментаря.
6. Дочекатися завершення запиту і перевірити повідомлення та форму.

**Expected Result**
F-90: голос зараховується лише разом із коментарем. Порожній Comment має бути відхилено без підтвердження успішного голосу. GUI-80 визначає заміну форми після успіху: саме ця заміна разом із Thank you for your vote! підтвердила небажане прийняття. Дослівний текст помилки SRS не задає.

**Actual Result**
Veneno: Comment порожній (length=0, required=false), Votes 2480. Один натиск Vote! без вводу; після завершення Thank you for your vote!, Comment/Vote! зникли, Votes 2481. Підтвердження прийняття — повідомлення й заміна форми; приріст спільного лічильника лише додатковий факт.

**Environment**
https://buggy.justtestit.org/; первинний прогін 2026-11-17. Для історичних звітів ENV-01/03 з archive-run3, повторна перевірка 17.11: ENV-04 — CloudBrowser Chrome/CDP, desktop 1363 CSS px; 17.11.2026; власна тестова сесія vasya1 підготовлена користувачем захищеним входом; точні версії Chrome/OS не отримано. Роль: власний зареєстрований тестовий користувач.

**Screenshot / Attachment**
before.jpg + before.json: порожній Comment і доступна Vote!; after.jpg + after.json: успіх, форми немає, Votes 2480→2481. Зображення не редагувалися.

- [BUG-113-before.jpg](attachments/BUG-113-before.jpg)
- [BUG-113-before.json](attachments/BUG-113-before.json)
- [BUG-113-after.jpg](attachments/BUG-113-after.jpg)
- [BUG-113-after.json](attachments/BUG-113-after.json)

**Retest 17.11:** Новий відтворений дефект порожнього коментаря на Veneno; успіх після рівно одного submit.

## Deferred — не входять до квоти

| BUG | Причина | Подальша дія |
|---|---|---|
| BUG-102 | href логотипа /broken є спостереженням, але GUI-10 не задає його ціль | Погодити очікуваний маршрут |
| BUG-104 | Склад меню/активний пункт Overall Rating не визначено | Погодити навігаційний контракт |
| BUG-110 | Верхня межа Age=95 не визначена GUI-45 | Погодити верхню межу; TC-027 Blocked |

Первісні повні Deferred-звіти й докази збережені в [bug-reports.md](bug-reports.md).
