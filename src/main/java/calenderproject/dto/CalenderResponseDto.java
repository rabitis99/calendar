package calenderproject.dto;

import calenderproject.entity.Calender;
import lombok.Getter;

@Getter
public class CalenderResponseDto {
    int id;
    String name;
    String date;
    String revisedDate;
    String password;
    String contents;

    //아래함수들의 목적은 일단 하나씩 타이핑하기에는 실수도 많고 가독성이 떨어져서 세터를 함수화 시켰습니다.


    //세터인데 calender 일일이 코딩하기 힘들어서 세터를 구현(오타예방)
    public void CalenderResponseDtoCalender(Calender calender){
        this.name=calender.getName();
        this.date=calender.getDate();
        this.password=calender.getPassword();
        this.contents=calender.getContents();
        this.id=calender.getId();
    }
    //세터인데 레파지토리용
    public void CalenderResponseDtoRepository(int id,String name,String contents,String date){
        this.name=name;
        this.date=date;
        this.contents=contents;
        this.id=id;
    }
    //여기서 생성자로 공통점을 묶어주고 setter로 받는것이 가장 깔끔한것인가? 함수를 사용한다면 느려지는가? 왜냐하면 하나의 객체이니깐
}
