# BullSheepFood

1.  API:
    * Вхід в додаток за допомогою соціальної мережі Facebook через Facebook API
Firebase ML Kit - Machine Learning tool для розпізнавання тексту з картинок, натренована модель яка використовується локально для англійської мови. Для мов не Latin-based потрібно використовувати Cloud рішення ML kit
Food Database API- інструменти для пошуку даних про харчування для загальних продуктів. Крім того, в ньому використовується NLP (Natural Language Processing), що дозволяє витягати харчові об'єкти з неструктурованого тексту.
2.  Категорія: Трекер( трекер поживності та харчування) 
3. Інструкція використання:
    * Запустивши додаток перед користувачем з’являється екран входу. Є дві можливі опції: увійти через соціальну мережу Facebook та увійти ввівши логін та пароль(екран реєстрації на даному етапі не передбачений).
    * Після того, як користувач увійшов відкривається екран статистики, де лежить статистика спожитих білків, жирів, вуглеводів та калорій за кожен окремий день тижня. Графіки інтерактивні, натиснувши на одну з частин графіку, вона збільшиться. Також графік крутиться. 
    * Внизу є меню, яке переводить користувача на екрани статистики, додавання продукту та раціону на день. 
    * Також є кнопка “додати”, яка дозволяє додати продукт та створити раціон на день. Додавання продукту відбувається двома способами: вручну та за допомогою сканування етикетки продукту. Сканування працює тільки з Latin-based мовами.  Machine Learning tool для розпізнавання тексту з картинок, натренована модель, яка використовується локально для англійської мови. Для мов не Latin-based потрібно використовувати Cloud рішення ML kit. В подальших рішеннях краще використовувати свою pre-trained модель TensorFlow Lite.
