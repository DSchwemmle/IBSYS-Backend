package com.ibsys.ibsysbackend.dto;

import lombok.*;

import java.util.List;

@Builder
@Getter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class MissingPartDto {
    private Integer id;
    private List<WaitingListStockWorkplaceDto> workplace;
}