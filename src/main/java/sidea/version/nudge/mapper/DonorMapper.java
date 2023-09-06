package sidea.version.nudge.mapper;

import org.apache.ibatis.annotations.Mapper;
import sidea.version.nudge.dto.DonorDto;

import java.util.List;

@Mapper
public interface DonorMapper {

    public List<DonorDto> getDonorList() throws Exception;
}
