package jpabook.jpashop.service.query;

import jpabook.jpashop.api.OrderSimpleApiController;
import jpabook.jpashop.domain.Order;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static java.util.stream.Collectors.toList;


@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class OrderQueryService {

    // 복잡한 화면을 출력하기 위한 변환 로직을 수행하는
    // (예시)
//    public List<OrderSimpleApiController.SimpleOrderDto> ordersV3() {
//        List<Order> orders = orderRepository.findAllWithMemberDelivery();
//        List<OrderSimpleApiController.SimpleOrderDto> result = orders.stream()
//                .map(o -> new OrderSimpleApiController.SimpleOrderDto(o))
//                .collect(toList());
//        return result; // v3와 v2는 결과적으로는 똑같지만, db로 날아가는 쿼리가 다르다 (쿼리가 단 한번!)
//    }

}
