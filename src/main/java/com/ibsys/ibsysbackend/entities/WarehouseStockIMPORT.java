package com.ibsys.ibsysbackend.entities;

import com.ibsys.ibsysbackend.dto.ArticleDto;
import lombok.*;

import java.util.List;

@Builder
@Getter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class WarehouseStockIMPORT {
    private List<ArticleDto> article;
    private String totalstockvalue;
}
