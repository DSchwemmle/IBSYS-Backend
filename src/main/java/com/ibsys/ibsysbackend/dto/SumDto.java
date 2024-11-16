package com.ibsys.ibsysbackend.dto;

import lombok.*;

@Builder
@Getter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class SumDto {
    private int setupevents;
    private long idletime;
    private String wageidletimecosts;
    private String wagecosts;
    private String machineidletimecosts;
}
