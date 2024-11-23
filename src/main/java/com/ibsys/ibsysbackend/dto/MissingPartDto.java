package com.ibsys.ibsysbackend.dto;

import com.ibsys.ibsysbackend.dto.WaitingListStockWorkplaceDto;
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