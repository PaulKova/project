package com.amr.project.converter.mappers;

import com.amr.project.converter.CycleAvoidingMappingContext;
import com.amr.project.converter.MapperInterface;
import com.amr.project.model.dto.UserDto;
import com.amr.project.model.entity.*;
import org.mapstruct.*;


@Mapper(builder = @Builder(disableBuilder = true), componentModel = "spring", uses = {AddressMapper.class, FavoriteMapper.class,
        DiscountMapper.class, FeedbackMapper.class,
        ReviewMapper.class, FavoriteMapper.class, ImageMapper.class})

public interface UserMapper extends MapperInterface<UserDto, User> {

    @Override
    @Mappings({
            @Mapping(target = "couponIds", source = "entity.coupons", qualifiedByName = "couponToIds1"),
            @Mapping(target = "orderIds", source = "entity.orders", qualifiedByName = "orderToIds1"),
            @Mapping(target = "shopIds", source = "entity.shops", qualifiedByName = "shopToIds1"),
            @Mapping(target = "chatIds", source = "entity.chats", qualifiedByName = "chatToIds1")
    })
    UserDto toDto(User entity, CycleAvoidingMappingContext cycleAvoidingMappingContext);

    @Named("couponToIds1")
    public static Long couponToIds(Coupon coupon) {
        return coupon.getId();
    }
    @Named("orderToIds1")
    public static Long orderToIds(Order order) {
        return order.getId();
    }
    @Named("shopToIds1")
    public static Long shopToIds(Shop shop) {
        return shop.getId();
    }
    @Named("chatToIds1")
    public static Long chatToIds(Chat chat) {
        return chat.getId();
    }


//
//    @Override
//    @Mappings({
//            @Mapping(source = "couponIds", target = "entity.coupons", qualifiedByName = "idsToCoupons"),
//            @Mapping(source = "orderIds", target = "entity.orders", qualifiedByName = "idsToOrders"),
//            @Mapping(source = "shopIds", target = "entity.shops", qualifiedByName = "idsToShops"),
//            @Mapping(source = "chatIds", target = "entity.chats", qualifiedByName = "idsToChats")
//    })
//    User toEntity(UserDto userDto, CycleAvoidingMappingContext cycleAvoidingMappingContext);

//    @Named("couponToIds")
//    public static Long idsToCoupons(Coupon coupon) {
//        return coupon.getId();
//    }
//    @Named("orderToIds")
//    public static Long idsToOrders(Order order) {
//        return order.getId();
//    }
//    @Named("shopToIds")
//    public static Long idsToShops(Shop shop) {
//        return shop.getId();
//    }
//    @Named("chatToIds")
//    public static Long idsToChats(Chat chat) {
//        return chat.getId();
//    }

//@Override
//    @Mapping(source = "coupons", target = "couponIds", qualifiedByName = "countCoupons")
//    UserDto toDto(User user, CycleAvoidingMappingContext cycleAvoidingMappingContext);
//
//    @Named("countCoupons")
//    default Long getCoupons(List<Coupon> coupons) {
//        if (coupons == null) {
//            return Long.valueOf(0);
//        }
//        return Long.valueOf(coupons.size());
//    }
//}


//    default List<Long> getCouponIds(List<Coupon> listEntities, CycleAvoidingMappingContext cycleAvoidingMappingContext) {
//        List<Coupon> couponList = cycleAvoidingMappingContext.getMappedInstance(listEntities, List.class);
//        List<Long> ids = couponList.stream().map(Coupon::getId).collect(Collectors.toList());
//        return ids;
//    }

//to add manually to Impl
// userDto.setCouponIds(userMapper.getCouponIds(entity.getCoupons(), cycleAvoidingMappingContext));


}