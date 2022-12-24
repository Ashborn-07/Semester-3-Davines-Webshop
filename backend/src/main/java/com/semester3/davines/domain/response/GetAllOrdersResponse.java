package com.semester3.davines.domain.response;

import com.semester3.davines.domain.Order;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class GetAllOrdersResponse {
    private List<Order> orders;
}
