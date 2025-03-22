package calenderproject.repository;

import calenderproject.dto.CalenderRequestDto;
import calenderproject.dto.CalenderResponseDto;
import calenderproject.entity.Calender;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@Repository
public class CalenderRepositoryImpl implements CalenderRepository {

    private final JdbcTemplate jdbcTemplate;

    public CalenderRepositoryImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public CalenderResponseDto creatCalender(Calender calender) {

        SimpleJdbcInsert jdbcInsert = new SimpleJdbcInsert(jdbcTemplate);
        jdbcInsert.withTableName("calender").usingGeneratedKeyColumns("id");

        Map<String, Object> parameters = new HashMap<>();
        parameters.put("name", calender.getName());
        parameters.put("contents", calender.getContents());
        parameters.put("date", calender.getDate());
        parameters.put("password", calender.getPassword());


        Number key = jdbcInsert.executeAndReturnKey(new MapSqlParameterSource(parameters));
        calender.setId(key.intValue());
        CalenderResponseDto calenderResponseDto = new CalenderResponseDto();
        calenderResponseDto.CalenderResponseDtoCalender(calender);//id가 변화된 객체
        return calenderResponseDto;
    }

    @Override
    public List<CalenderResponseDto> findAllCalender() {

        return jdbcTemplate.query("select name, contents, date, id from calender", calenderRowMapper());
    }


    //여가서 중복된 이름을 갖는다면 큰일난다 why?동일이름 구성전부 바뀜 & foreach 사용불가 두개이상일 가능성이 너무 크다
    // 맨처음 저장된 아이
    @Override
    public CalenderResponseDto findCalenderByName(CalenderRequestDto calenderRequestDto) {
        String name=calenderRequestDto.getName();
        return jdbcTemplate.query("SELECT id, name, contents, date FROM calender WHERE name = ?", calenderRowMapper(), name).get(0);
    }


    @Override
    public int reviseCalender(CalenderRequestDto calenderRequestDto) {
        String name=calenderRequestDto.getName();
        String contents=calenderRequestDto.getContents();
        String password =calenderRequestDto.getPassword();
        List<Calender> result=jdbcTemplate.query("select password from calender where name =?", calenderRowMapper2(),name);
        String originPassword=result.get(0).getPassword();
        if (password.equals(originPassword)){
            return jdbcTemplate.update("update calender set name = ?, contents = ? where name=?",name,contents,name);
        }
        return 0;
    }

    @Override
    public int deleteCalender(CalenderRequestDto calenderRequestDto) {


        return jdbcTemplate.update("delete from calender where name =?", calenderRequestDto.getName());
    }

    private RowMapper<CalenderResponseDto> calenderRowMapper() {
        return new RowMapper<CalenderResponseDto>() {
            @Override
            public CalenderResponseDto mapRow(ResultSet rs, int rowNum) throws SQLException {

                CalenderResponseDto calenderResponseDto = new CalenderResponseDto();

                calenderResponseDto.CalenderResponseDtoRepository(
                        rs.getInt("id"),
                        rs.getString("name"),
                        rs.getString("contents"),
                        rs.getString("date")
                );
                return calenderResponseDto;
            }
        };
    }


    //패스워드 확인용
    private RowMapper <Calender> calenderRowMapper2() {
        return new RowMapper<Calender>() {
            @Override
            public Calender mapRow(ResultSet rs, int rowNum) throws SQLException {

                Calender calender=new Calender();
                calender.setPassword(rs.getString("password"));

                return calender;
            }
        };
    }

}
