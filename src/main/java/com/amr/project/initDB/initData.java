package com.amr.project.initDB;

import com.amr.project.initDB.repository.*;
import com.amr.project.model.entity.*;
import com.amr.project.model.enums.Roles;
import com.amr.project.model.enums.Status;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.Month;
import java.util.Calendar;
import java.util.Date;
import java.util.Set;

@Component
public class initData {

    private final ReviewRepository reviewRepository;
    private final OrdersRepository ordersRepository;
    private final ItemRepository itemRepository;
    private final FeedBackRepository feedBackRepository;
    private final FavoriteRepository favoriteRepository;
    private final DiscountRepository discountRepository;
    private final CouponRepository couponRepository;
    private final CountryRepository countryRepository;
    private final CityRepository cityRepository;
    private final ChatRepository chatRepository;
    private final CategoryRepository categoryRepository;
    private final UserRepository userRepository;
    private final ShopRepository shopRepository;

    public initData(ReviewRepository reviewRepository, OrdersRepository ordersRepository,
                    ItemRepository itemRepository, FeedBackRepository feedBackRepository,
                    FavoriteRepository favoriteRepository, DiscountRepository discountRepository,
                    CouponRepository couponRepository, CountryRepository countryRepository,
                    CityRepository cityRepository, ChatRepository chatRepository,
                    CategoryRepository categoryRepository, UserRepository userRepository,
                    ShopRepository shopRepository) {
        this.reviewRepository = reviewRepository;
        this.ordersRepository = ordersRepository;
        this.itemRepository = itemRepository;
        this.feedBackRepository = feedBackRepository;
        this.favoriteRepository = favoriteRepository;
        this.discountRepository = discountRepository;
        this.couponRepository = couponRepository;
        this.countryRepository = countryRepository;
        this.cityRepository = cityRepository;
        this.chatRepository = chatRepository;
        this.categoryRepository = categoryRepository;
        this.userRepository = userRepository;
        this.shopRepository = shopRepository;
    }

    @PostConstruct
    public void initializationDB() {
        City Berlin = City.builder().name("Berlin").build();
        City LosSantos = City.builder().name("LosSantos").build();
        City SanAndreas = City.builder().name("SanAndreas").build();
        City Vegas = City.builder().name("Vegas").build();
        City Frankfurt = City.builder().name("Frankfurt").build();
        cityRepository.save(Berlin);
        cityRepository.save(LosSantos);
        cityRepository.save(SanAndreas);
        cityRepository.save(Vegas);
        cityRepository.save(Frankfurt);

/////////////////////////////////////////////////Country////////////////////////////////////////////////////////////
        Country USA = new Country();
        USA.setName("USA");
        //USA.setCities(Set.of(LosSantos, Vegas, SanAndreas));

        Country Germany = new Country();
        Germany.setName("Germany");
        //Germany.setCities(Set.of(Berlin, Frankfurt));

        countryRepository.save(USA);
        countryRepository.save(Germany);
/////////////////////////////////////////////////Address////////////////////////////////////////////////////////////
        Address address1 = Address.builder()
                .city(Berlin)
                .cityIndex("11111")
                .street("user1_street")
                .house("user1_house")
                .build();
        Address address2 = Address.builder()
                .city(Berlin)
                .cityIndex("11111")
                .street("user2_street")
                .house("user2_house")
                .build();
        Address address3 = Address.builder()
                .city(Berlin)
                .cityIndex("11111")
                .street("user3_street")
                .house("user3_house")
                .build();
/////////////////////////////////////////////////User////////////////////////////////////////////////////////////
        User user1 = User.builder()
                .email("user1@mail.com")
                .username("user1")
                .password("user1")
                .activate(true)
                .activationCode("some_code")
                .isUsingTwoFactorAuth(false)
                .secret("secret?")
                .role(Roles.USER)
                .userInfo(null)
                .favorite(null)
                .address(null)
                .images(null)
                .coupons(null)
                .cart(null)
                .orders(null)
                .reviews(null)
                .shops(null)
                .discounts(null)
                .messages(null)
                .chats(null)
                .feedbacks(null)
                .build();
        User user2 = User.builder()
                .email("user2@mail.com")
                .username("user2")
                .password("user2")
                .activate(true)
                .activationCode("some_code")
                .isUsingTwoFactorAuth(false)
                .secret("secret?")
                .role(Roles.USER)
                .userInfo(null)
                .favorite(null)
                .address(null)
                .images(null)
                .coupons(null)
                .cart(null)
                .orders(null)
                .reviews(null)
                .shops(null)
                .discounts(null)
                .messages(null)
                .chats(null)
                .feedbacks(null)
                .build();
        User user3 = User.builder()
                .email("user3@mail.com")
                .username("user3")
                .password("user3")
                .activate(true)
                .activationCode("some_code")
                .isUsingTwoFactorAuth(false)
                .secret("secret?")
                .role(Roles.USER)
                .userInfo(null)
                .favorite(null)
                .address(null)
                .images(null)
                .coupons(null)
                .cart(null)
                .orders(null)
                .reviews(null)
                .shops(null)
                .discounts(null)
                .messages(null)
                .chats(null)
                .feedbacks(null)
                .build();
        userRepository.save(user1);
        userRepository.save(user2);
        userRepository.save(user3);
/////////////////////////////////////////////////Shop////////////////////////////////////////////////////////////
        Shop shop1 = Shop.builder()
                .name("shop1")
                .email("shop1@mail.com")
                .phone("shop1_phone")
                .description("shop1_description")
                .count(0)
                .rating(0)
                .location(null)
                .items(null)
                .reviews(null)
                .logo(null)
                .user(user1)
                .cartItem(null)
                .feedback(null)
                .discounts(null)
                .favorites(null)
                .address(null)
                .coupons(null)
                .isModerated(true)
                .isModerateAccept(true)
                .moderatedRejectReason("cozTest")
                .isPretendentToBeDeleted(false)
                .build();
        Shop shop2 = Shop.builder()
                .name("shop2")
                .email("shop2@mail.com")
                .phone("shop2_phone")
                .description("shop2_description")
                .count(0)
                .rating(0)
                .location(null)
                .items(null)
                .reviews(null)
                .logo(null)
                .user(user1)
                .cartItem(null)
                .feedback(null)
                .discounts(null)
                .favorites(null)
                .address(null)
                .coupons(null)
                .isModerated(true)
                .isModerateAccept(true)
                .moderatedRejectReason("cozTest")
                .isPretendentToBeDeleted(false)
                .build();
        Shop shop3 = Shop.builder()
                .name("shop3")
                .email("shop3@mail.com")
                .phone("shop3_phone")
                .description("shop3_description")
                .count(0)
                .rating(0)
                .location(null)
                .items(null)
                .reviews(null)
                .logo(null)
                .user(user1)
                .cartItem(null)
                .feedback(null)
                .discounts(null)
                .favorites(null)
                .address(null)
                .coupons(null)
                .isModerated(true)
                .isModerateAccept(true)
                .moderatedRejectReason("cozTest")
                .isPretendentToBeDeleted(false)
                .build();
        shopRepository.save(shop1);
        shopRepository.save(shop2);
        shopRepository.save(shop3);
/////////////////////////////////////////////////Category////////////////////////////////////////////////////////////
        Category category1 = Category.builder().name("cat1").build();
        Category category2 = Category.builder().name("cat2").build();
        Category category3 = Category.builder().name("cat3").build();
        Category category4 = Category.builder().name("cat4").build();
        Category category5 = Category.builder().name("cat5").build();
        categoryRepository.save(category1);
        categoryRepository.save(category2);
        categoryRepository.save(category3);
        categoryRepository.save(category4);
        categoryRepository.save(category5);
/////////////////////////////////////////////////Item////////////////////////////////////////////////////////////
        Item item1 = Item.builder()
                .name("item1")
                .basePrice(new BigDecimal(100))
                .price(new BigDecimal(210))
                .count(20)
                .rating(5.0)
                .description("description")
                .discount(0)
                .category(category1)
                .cartItem(null)
                .images(null)
                .reviews(null)
                .favorites(null)
                .orders(null)
                .shop(shop1)
                .isModerated(true)
                .isModerateAccept(true)
                .moderatedRejectReason("coz")
                .isPretendedToBeDeleted(false)
                .build();
        Item item2 = Item.builder()
                .name("item2")
                .basePrice(new BigDecimal(200))
                .price(new BigDecimal(410))
                .count(20)
                .rating(5.0)
                .description("description")
                .discount(0)
                .category(category1)
                .cartItem(null)
                .images(null)
                .reviews(null)
                .favorites(null)
                .orders(null)
                .shop(shop1)
                .isModerated(true)
                .isModerateAccept(true)
                .moderatedRejectReason("coz")
                .isPretendedToBeDeleted(false)
                .build();
        Item item3 = Item.builder()
                .name("item3")
                .basePrice(new BigDecimal(500))
                .price(new BigDecimal(910))
                .count(10)
                .rating(3.0)
                .description("description")
                .discount(0)
                .category(category2)
                .cartItem(null)
                .images(null)
                .reviews(null)
                .favorites(null)
                .orders(null)
                .shop(shop1)
                .isModerated(true)
                .isModerateAccept(true)
                .moderatedRejectReason("coz")
                .isPretendedToBeDeleted(false)
                .build();
        Item item4 = Item.builder()
                .name("item4")
                .basePrice(new BigDecimal(900))
                .price(new BigDecimal(1800))
                .count(40)
                .rating(2.0)
                .description("description")
                .discount(0)
                .category(category2)
                .cartItem(null)
                .images(null)
                .reviews(null)
                .favorites(null)
                .orders(null)
                .shop(shop1)
                .isModerated(true)
                .isModerateAccept(true)
                .moderatedRejectReason("coz")
                .isPretendedToBeDeleted(false)
                .build();
        Item item5 = Item.builder()
                .name("item5")
                .basePrice(new BigDecimal(100))
                .price(new BigDecimal(210))
                .count(2000)
                .rating(3.0)
                .description("description")
                .discount(0)
                .category(category3)
                .cartItem(null)
                .images(null)
                .reviews(null)
                .favorites(null)
                .orders(null)
                .shop(shop1)
                .isModerated(true)
                .isModerateAccept(true)
                .moderatedRejectReason("coz")
                .isPretendedToBeDeleted(false)
                .build();
        Item item6 = Item.builder()
                .name("item6")
                .basePrice(new BigDecimal(100))
                .price(new BigDecimal(210))
                .count(200)
                .rating(4.0)
                .description("description")
                .discount(0)
                .category(category3)
                .cartItem(null)
                .images(null)
                .reviews(null)
                .favorites(null)
                .orders(null)
                .shop(shop1)
                .isModerated(true)
                .isModerateAccept(true)
                .moderatedRejectReason("coz")
                .isPretendedToBeDeleted(false)
                .build();
        itemRepository.save(item1);
        itemRepository.save(item2);
        itemRepository.save(item3);
        itemRepository.save(item4);
        itemRepository.save(item5);
        itemRepository.save(item6);
/////////////////////////////////////////////////FeedBack////////////////////////////////////////////////////////////
        Feedback feedback1 = Feedback.builder()
                .reason("reason1")
                .fullText("full_text_fb1")
                .dateTime(LocalDate.now().atTime(12, 33))
                .username(user1.getUsername())
                .build();
        Feedback feedback2 = Feedback.builder()
                .reason("reason2")
                .fullText("full_text_fb2")
                .dateTime(LocalDate.now().atTime(LocalTime.now()))
                .username(user2.getUsername())
                .build();
        feedBackRepository.save(feedback1);
        feedBackRepository.save(feedback2);
/////////////////////////////////////////////////Coupons////////////////////////////////////////////////////////////
        Coupon coupon1 = new Coupon();
        Coupon coupon2 = new Coupon();
        Coupon coupon3 = new Coupon();
        couponRepository.save(coupon1);
        couponRepository.save(coupon2);
        couponRepository.save(coupon3);
//        User userForCoupon1 = userRepository.findByEmail("user1@mail.com");
//        User userForCoupon2 = userRepository.findByEmail("user1@mail.com");
//        User userForCoupon3 = userRepository.findByEmail("user1@mail.com");
        user1.setCoupons(Set.of(coupon1));
        user2.setCoupons(Set.of(coupon1, coupon2));
        user3.setCoupons(Set.of(coupon1, coupon3));
        //userRepository.save(user1);
/////////////////////////////////////////////////Discounts////////////////////////////////////////////////////////////
        Discount discount1 = Discount.builder()
                .minOrder(1000)
                .percentage(5)
                .fixedDiscount(2)
                .shop(shop1)
                .build();
        Discount discount2 = Discount.builder()
                .minOrder(3500)
                .percentage(15)
                .fixedDiscount(2)
                .shop(shop2)
                .build();
        discountRepository.save(discount1);
        discountRepository.save(discount2);
/////////////////////////////////////////////////Chat////////////////////////////////////////////////////////////
//        Chat chat1 = Chat.builder()
//                .users(Set.of(user1, user2))
//                .build();
//        Message message1Chat1 = Message.builder()
//                .date(new Date())
//                .textMessage("chert poberi1")
//                .viewed(true)
//                .user(user1)
//                .chat(chat1)
//                .build();
//        Message message2Chat1 = Message.builder()
//                .date(new Date())
//                .textMessage("chert poberi2")
//                .viewed(true)
//                .user(user2)
//                .chat(chat1)
//                .build();
//
//        chat1.setMessages(Set.of(message1Chat1, message2Chat1));
//        chatRepository.save(chat1);
//
//        Chat chat2 = Chat.builder()
//                .users(Set.of(user2, user3))
//                .build();
//        Message message1Chat2 = Message.builder()
//                //.date()
//                .textMessage("chert!!!!!poberi1")
//                .viewed(true)
//                .user(user2)
//                .chat(chat2)
//                .build();
//        Message message2Chat2 = Message.builder()
//                //.date()
//                .textMessage("chert!!!!!poberi2")
//                .viewed(true)
//                .user(user3)
//                .chat(chat2)
//                .build();
//
//        chat2.setMessages(Set.of(message1Chat2, message2Chat2));
//        chatRepository.save(chat2);
/////////////////////////////////////////////////Favourite////////////////////////////////////////////////////////////
        Favorite favorite1 = Favorite.builder()
                .shops(Set.of(shop1))
                .items(Set.of(item1))
                .user(user1)
                .build();
        Favorite favorite2 = Favorite.builder()
                .shops(Set.of(shop2))
                .items(Set.of(item2))
                .user(user2)
                .build();
        Favorite favorite3 = Favorite.builder()
                .shops(Set.of(shop3))
                .items(Set.of(item3))
                .user(user3)
                .build();
        favoriteRepository.save(favorite1);
        favoriteRepository.save(favorite2);
        favoriteRepository.save(favorite3);
/////////////////////////////////////////////////Orders////////////////////////////////////////////////////////////
//        Order order1 = Order.builder()
//                .orderDate(Calendar.getInstance())
//                .expectedDeliveryDate(Calendar.getInstance())
//                .grandTotal(item1.getPrice().add(item2.getPrice()))
//                .currency("dollar")
//                .description("help me")
//                .status(Status.START)
//                .user(user1)
//                .itemsInOrder(Set.of(item1, item2))
//                .address(null)
//                .build();
//        Order order2 = Order.builder()
//                .orderDate(Calendar.getInstance())
//                .expectedDeliveryDate(Calendar.getInstance())
//                .grandTotal(item3.getPrice().add(item2.getPrice()))
//                .currency("dollar")
//                .description("help me")
//                .status(Status.START)
//                .user(user1)
//                .itemsInOrder(Set.of(item1, item2))
//                .address(null)
//                .build();
//        ordersRepository.save(order1);
//        ordersRepository.save(order2);
/////////////////////////////////////////////////Review////////////////////////////////////////////////////////////
    }
}
