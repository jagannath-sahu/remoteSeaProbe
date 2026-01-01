package com.dxc.remoteSeaProbe.mapper;

import com.dxc.remoteSeaProbe.dto.CreateProbeRequest;
import com.dxc.remoteSeaProbe.dto.ProbeResponse;
import com.dxc.remoteSeaProbe.persistence.entity.RemoteSeaProbe;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

@Mapper(
        componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.ERROR
)
public interface RemoteSeaProbeMapper {
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    RemoteSeaProbe toEntity(CreateProbeRequest request);

    @Mapping(source = "initialLatitude", target = "latitude")
    @Mapping(source = "initialLongitude", target = "longitude")
    ProbeResponse toResponse(RemoteSeaProbe remoteSeaProbe);
}
