# ВНИМАНИЕ!!! ЭТО ПРИМЕР ДОКУМЕНТАЦИИ
#ВАМ ТОЖЕ ОЧЕНЬ ПРИГОДИТСЯ

## Сущности

[Схема базы данных](https://dbdiagram.io/embed/614c9d0e825b5b0146107aac)

```
* - поля ссылающиеся на другие Entity;
** - поля ссылающиеся на Enum.
```

### Address:

#### Поля:

- **id** - уникальный идентификационный номер адреса;
- **cityIndex** - поле, содержит почтовый индекс;
- **country** - поле, содержит Страну*;
- **city** - поле, содержит Город*;
- **street** - поле, содержит название улицы;
- **house** - поле, содержит дом (String);
- **users** -List связывающие адреас с конкретными Пользователями(@ManyToMany)*.

```
Сущность представляет собой набор данных предназначенных для определения 
адреса доставки заказа/ расположения магазина / расположения пользователя.
```

### CartItem:

#### Поля:

- **id** - уникальный идентификационный номер карточки товара;
- **quantity** - поле, содержит количество товаров.
- **user** - поле, содержит User* который выбрал товар(@ManyToOne);
- **shop** - поле, содержит Магазин* в котором был выбран товар(@OneToOne);
- **item** - коллекция(List) хранящая выбранный Товар(@OneToMany);

#### Методы:

- **getSubTotal()**- метод который подсчитывает стоимость товара на основании предоставленной скидки и количества
  выбранного товара.

```
Сущность предназначенная для отображения товара который пользователь пожелал 
положить в свою корзину для дальнейшего формирования заказа. Для каждого уникального 
товара создается отдельная сущность с указанием его желаемого количества для приобретения.
```

### Category:

#### Поля:

- **id** - уникальный идентификационный номер категории товара;
- **name** - уникальное поле, содержит название категории товара.
- **item** - коллекция(List) предметов, находящихся в данной категории(@ManyToMany)

```
Сущность для создания и присвоения категории товару. Позволяет реализовать функционал 
для фильтрации товара по интересующим пользователя категориям.
```

### Chat:

#### Поля:

- **id** - уникальный идентификационный номер чата;
- **members** - список Пользователей* которые участвуют в чате;
- **messages** - список Сообщений* который содержит чат.

```
Сущность представляющая собой чат для общения пользователей, который хранит 
список пользователей и список пересылаемых сообщений в чате.
```

### City:

#### Поля:

- **id** - уникальный идентификационный номер города;
- **name** - уникальное поле хранящие название города;
- **country** - поле, содержит Страну* в которой находится город;
- **addresses** - поле, содержит список Адресов* относящихся к данной улице.

```
Сущность предоставляющая город, который входит в состав Адреса*.
```

### Country:

#### Поля:

- **id** - уникальный идентификационный номер страны;
- **name** - уникальное поле хранящие название страны;
- **cities** - список Городов* находящихся в данной стране.

```
Сущность представляющая страну, которая входит в состав Адреса* 
и имеет связь с Городом*
```


### Coupon:

#### Поля:

- **id** - уникальный идентификационный номер страны;
- **start** - Calendar value
- **end** - Calendar value
- **shop** - поле, содержащее магазин, который владеет купоном(@ManyToOne);
- **usersInCoupon** - коллекция (List) юзеров, к которым пренадлжит купон.

```
Сущность представляющая купон, котроый пренадлежит пользователю
```



### Discount:

#### Поля:

- **id** - уникальный идентификационный номер скидки;
- **minOrder** - минимальный заказ для получения скидки;
- **percentage** - поле, содержит скидку в % от покупки;
- **fixedDiscount** - фиксированное значение скидки от величины покупки;
- **shop** - поле, содержит Магазин* который выдал скидку;
- **user** - скидка магазина выданная конкретному Пользователю*.
- **users** - коллекция List<User> "принадлежащая" List<Discount>(@ManyToMany)

```
Сущность предоставляющая поля для определения величины скидки. 
Имеет связи с Магазином*, в котором была выдана и Пользователем*, 
которому была выдана скидка.
```

### Favorite:

#### Поля:

- **id** - уникальный идентификационный номер избранного товара/магазина;
- **shops** - список избранных Магазинов*;
- **items** - список избранных Товаров*;
- **user** - определяет Пользователя* создавшего свой набор избранного.

```
Сущность  для хранения избранного определенного пользователя. Хранит 
избранные  магазины и/или товары.
```

### Image:

#### Поля:

- **id** - уникальный идентификационный номер изображения;
- **url** - ссылка на сторонний ресурс хранящий изображение;
- **picture** - поле хранящие код для представления изображения;
- **isMain** - поле определяющее статус изображения как основное для отображения;
- **user** - поле, содержит Пользователя*.

```
Сущность для хранения изображений для товара/ логотипа магазина/ 
изображения профиля пользователя. При загрузки изображений, они хранятся в базе 
данных в виде массива байт. Можно загрузить ссылку на сторонний ресурс для изображения.
Ссылка будет храниться в базе данных. Есть поле-флаг, которое помечает какое изображение 
будет отображаться на витрине, если товар имеет несколько изображений.
```

### Item:

#### Поля:

- **id** - уникальный идентификационный номер товара;
- **name** - поле, содержит название товара;
- **price**- поле, содержит цену товара;
- **categories** - коллекция(List) Категорий* к которым относится данный товар(@ManyToMany);
- **cartItem** - поле, хранящее корзину, в которой лежит товар(@ManyToOne)
- **images** - список Изображений* для товара;
- **reviews** - список Отзывов* для товара;
- **count** - поле, содержит количество товара данного вида;
- **rating** - поле, содержит рейтинг товара в цифровом выражении;
- **description** - поле, содержит описание товара;
- **discount** - поле которое устанавливает величину скидки на товар;
- **shop** - описывает Магазин* в котором продается товар;
- **isModerated** - поле отражающее статус промодерировано/не промодерировано;
- **isModerateAccept** - поле отражающее статус после модерации, прошел модерацию/не прошел модерацию;
- **moderatedRejectReason** - поле описывающие причину отклонения товара модераторов;
- **isPretenderToBeDeleted** - поле-флаг переводящее товар в статус “на удаление”.

```
Сущность представляющая товар. Любой товар должен пройти модерацию у пользователя 
с необходимой ролью. После успешной модерации товар попадает на страницу доступную всем пользователям.
```

### Message:

#### Поля:

- **id** - уникальный идентификационный номер сообщения;
- **chat** - поле, содержит Чат* к которому относится сообщение; \\ вообще не думаю что сообщению нужно знать в каком чате оно лежит, для этого есть чат, который тянет сообщения
- **to** - поле, содержит какому Пользователю* адресовано сообщение;
- **from** - поле, содержит от какого Пользователя* пришло сообщение;
- **textMessage** - поле, содержит содержание сообщения;
- **viewed** - поле отражающее статус сообщения прочитано/не прочитано; \\хз нужно ли в авито
- Id - айдишник
- Text - текстовое сообщение (возможно с форматами потипу жирный шрифт курсив и тд)
- Files - коллекция прикрепленных файлов
- Date - Дата отправки сообщения
- from - отправитель
- To - получатель

```
Сущность представляющая возможность хранить сообщения. Используется 
для чата между пользователями.
```

### Order:

#### Поля:

- **id** - уникальный идентификационный номер заказа;
- **items** - список Товаров* входящих в заказ;
- **date** - поля хранящее дату заказа;
- **status** - поле, содержит Статус** заказа;
- **address** - поле, содержит Адрес* доставки;
- **total** - поле, содержит сумму(в денежном выражении) заказа;
- **user** - поле, содержит Пользователя* сделавшего заказ;(@ManyToOne)
- **buyerName** - поле, содержит имя получателя заказа;
- **buyerPhone** - поле, содержит телефон для связи с получателем заказа;

```
Сущность представляющая собой заказ формирующийся Пользователем* на 
основании товара помещенного в корзину. Имеет поле для определения статуса заказа.
```

### Review:

#### Поля:

- **id** - уникальный идентификационный номер отзыва;
- **dignity** - поле, содержит описание “плюсов”;
- **flaw** - поле, содержит описание “минусов”;
- **text** - поле, содержит тело сообщения;
- **date** - поле, содержит дату опубликования отзыва;
- **rating** - поле, указывает рейтинг(в числовом выражении) отзыва;
- **user** - поле, содержит Пользователя*, на которого оставили отзыв;
- **shop** - поле, содержит Магазин* на который оставили отзыв;
- **item** - поле, содержит Товар* на который оставили отзыв;
- **isModerated** - поле отражающее статус модерировано/не модерировано;
- **isModerateAccept** - поле, отражающее статус после модерации, прошел модерацию/не прошел модерацию;
- **moderatedRejectReason** - поле описывающие причину отклонения публикации отзыва;

```
Сущность представляющая собой отзыв на магазин/товар/пользователя.
```

### Role:

#### Поля:

- **id** - уникальный идентификационный номер роли;
- **name** - уникальное поле, содержит название роли;
- **users** - набор Пользователей* имеющих роль.

```
Сущность определяющая порядок прав пользования Пользователя* в системе.
```

### Shop:

#### Поля:

- **id** - уникальный идентификационный номер магазина;
- **name** - уникальное поле, содержит название магазина;
- **email** - поле, содержит е-маил адрес магазина;
- **phone** - поле, содержит номер телефона магазина;
- **description** - поле, содержит информацию о магазине;
- **location** - поле, содержит Страну* расположения магазина;
- **items** - поле, содержит список Товаров* продающихся в магазине;
- **reviews** - поле, содержит список Отзывов* на магазин;
- **logo** - поле, содержит Изображение* магазина(логотип);
- **rating** - поле показывающее рейтинг(в числовом выражении) магазина;
- **users** - List Пользователей* которые связаны с  магазинами(@ManyToMany);
- **cartItem** - поле, содержит корзину с товаром, принадлежащую магазину(@OneToOne)
- **discounts** - List<Discount> принадлежащих магазину(@OneToMany, однонаправленная)
- **feedback** - коллекция List feedback, замаплена на shop (@OneToMany, однонаправленная)
- **users** - коллекция List users(@ManyToMany, mappedBy = "shops"(в User))
- **discounts** - поле, содержит коллекцию Скидок* выдаваемую данным магазином;
- **isModerated** - поле, отражающее статус модерировано/не модерировано;
- **isModerateAccept** -поле отражающее статус после модерации, прошел модерацию/не прошел модерацию;
- **moderatedRejectReason** - поле описывающие причину отклонения по результатам модерации;
- **isPretenderToBeDeleted** - поле-флаг переводящее магазин в статус “на удаление”.

```
Сущность представляющая собой магазин, который предоставляет пользователю товар 
для покупки. Данная сущность должна проходить обязательную модерацию пользователем, 
с ролью определяющей такую возможность.
```

### User:

#### Поля:

- **id** - уникальный идентификационный номер пользователя;
- **email** - уникальный е-маил адрес пользователя;
- **username** - уникальный логин пользователя;
- **password** - пароль пользователя;
- **activate** - поле-флаг отвечающее за активацию учетной записи пользователя;
- **activationCode** - поле, содержит код для подтверждения активации учетной записи пользователя;
- **phone** - уникальное поле, содержит телефонный номер пользователя;
- **firstName** - поле, содержит имя пользователя;
- **lastName** - поле, содержит фамилию пользователя;
- **address** - List Адреса* нахождения пользователя(@ManyToMany);
- **roles** - поле, содержит сет доступных Ролей* для пользователя;
- **gender** - поле, содержит Пол** пользователя;
- **birthday** - поле, содержит дату-рождения пользователя;
- **images** - коллекция (List) Изображений* пользователя для профиля учетной записи(@OneToMany однонаправленная с JoinColumn);
- **coupons** - collection (List) купонов, принадлежащих юзеру(@ManyToMany)
- **cart** - коллекция List, содержит Карточки Товара* помещенную в корзину пользователя(@OneToMany);
- **orders** - коллекция List Заказов* пользователя(@OneToMany);
- **reviews** - коллекция List которые оставил пользователь(@OneToMany, однонаправленная с JoinColumn);
- **shops** - коллекция List Магазинов* принадлежащих пользователю(@ManyToMany);
- **favorite** - поле содержащее Избранное* пользователя;
- **discounts** - Коллекция List<Discount> предоставленные магазинами пользователю(@ManyToMany).

```
Сущность определяющая пользователя который может покупать и продавать товары. 
Для покупки товара необходимо поместить товар вначале в корзину заказов с помощью 
сущности CartItem, далее формировать заказ при помощи сущности Order. Продавать 
товар можно как физическое лицо, так и через магазин - юридическое лицо. Можно 
открывать несколько магазинов на одного пользователя.
```

## Промежуточные сущности

### chat_members:

#### Поля:

- **chat_id** - поле ссылающиеся на уникальный номер Чата*;
- **members_id** - поле ссылающиеся на уникальный номер Участников*.

```
Промежуточная сущность для реализации свзязи many-to-many Чата* с Участником*.
```

### chat_messages:

#### Поля:

- **chat_id** - поле ссылающиеся на уникальный номер Чата*;
- **messages_id** - поле ссылающиеся на уникальный номер Сообщения*.

```
Промежуточная сущность для реализации свзязи one-to-many Чата* с Сообщением*.
```

### country_city:

#### Поля:

- **country_id** - поле ссылающиеся на уникальный номер Страны*;
- **city_id** - поле ссылающиеся на уникальный номер Города*.

```
Промежуточная сущность для реализации свзязи one-to-many Страны* с Городом*.
```

### favorite_item:

#### Поля:

- **favorite_id** - поле ссылающиеся на уникальный номер Избранного*;
- **item_id** - поле ссылающиеся на уникальный номер Товара*.

```
Промежуточная сущность для реализации свзязи many-to-many Избранного* с Товаром*.
```

### favorite_shop:

#### Поля:

- **favorite_id** - поле ссылающиеся на уникальный номер Избранного*;
- **shop_id** - поле ссылающиеся на уникальный номер Магазина*.

```
Промежуточная сущность для реализации свзязи many-to-many Избранного* с Магазином*.
```

### item_category:

#### Поля:

- **item_id** - поле ссылающиеся на уникальный номер Товара*;
- **category_id** - поле ссылающиеся на уникальный номер Категории*.

```
Промежуточная сущность для реализации свзязи many-to-many Товара* с Категорией*.
```

### item_image:

#### Поля:

- **item_id** - поле ссылающиеся на уникальный номер Товара*;
- **image_id** - поле ссылающиеся на уникальный номер Изображения*.

```
Промежуточная сущность для реализации свзязи many-to-many Товара* с Изображением*.
```

### item_review:

#### Поля:

- **item_id** - поле ссылающиеся на уникальный номер Товара*;
- **review_id** - поле ссылающиеся на уникальный номер Отзыва*.

```
Промежуточная сущность для реализации свзязи one-to-many Товара* с Отзывом*.
```

### orders_item:

#### Поля:

- **orders_id** - поле ссылающиеся на уникальный номер Заказа*;
- **item_id** - поле ссылающиеся на уникальный номер Товара*.

```
Промежуточная сущность для реализации свзязи many-to-many Заказа* с Товаром*.
```

### shop_discount:

#### Поля:

- **shop_id** - поле ссылающиеся на уникальный номер Магазин*;
- **discount_id** - поле ссылающиеся на уникальный номер Скидки*.

```
Промежуточная сущность для реализации свзязи one-to-many Магазина* со Скидкой*.
```

### shop_item:

#### Поля:

- **shop_id** - поле ссылающиеся на уникальный номер Магазин*;
- **item_id** - поле ссылающиеся на уникальный номер Товара*.

```
Промежуточная сущность для реализации свзязи one-to-many Магазина* с Товаром*.
```

### shop_review:

#### Поля:

- **shop_id** - поле ссылающиеся на уникальный номер Магазин*;
- **review_id** - поле ссылающиеся на уникальный номер Отзыва*.

```
Промежуточная сущность для реализации свзязи one-to-many Магазина* с Отзывом*.
```

### user_cart:

#### Поля:

- **user_id** - поле ссылающиеся на уникальный номер Пользователя*;
- **cart_id** - поле ссылающиеся на уникальный номер Корзины*.

```
Промежуточная сущность для реализации свзязи many-to-many Пользователя* с Корзиной*.
```

### user_discount:

#### Поля:

- **user_id** - поле ссылающиеся на уникальный номер Пользователя*;
- **discount_id** - поле ссылающиеся на уникальный номер Скидки*.

```
Промежуточная сущность для реализации свзязи one-to-many Пользователя* со Скидкой*.
```

### user_orders:

#### Поля:

- **user_id** - поле ссылающиеся на уникальный номер Пользователя*;
- **orders_id** - поле ссылающиеся на уникальный номер Заказа*.

```
Промежуточная сущность для реализации свзязи one-to-many Пользователя* с Заказом*.
```

### user_review:

#### Поля:

- **user_id** - поле ссылающиеся на уникальный номер Пользователя*;
- **review_id** - поле ссылающиеся на уникальный номер Отзыва*.

```
Промежуточная сущность для реализации свзязи one-to-many Пользователя* с Отзывом*.
```

### user_role:

#### Поля:

- **user_id** - поле ссылающиеся на уникальный номер Пользователя*;
- **review_id** - поле ссылающиеся на уникальный номер Роли*.

```
Промежуточная сущность для реализации свзязи many-to-many Пользователя* с Ролью*.
```

### user_shop:

#### Поля:

- **user_id** - поле ссылающиеся на уникальный номер Пользователя*;
- **shop_id** - поле ссылающиеся на уникальный номер Магазина*.

```
Промежуточная сущность для реализации свзязи one-to-many Пользователя* с Магазином*.
```