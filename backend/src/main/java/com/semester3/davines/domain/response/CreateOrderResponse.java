package com.semester3.davines.domain.response;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class CreateOrderResponse {

    private Long orderId;
}
