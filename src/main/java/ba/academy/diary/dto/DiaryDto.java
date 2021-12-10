package ba.academy.diary.dto;

import java.util.Date;
import java.util.List;

public class DiaryDto {
  private Integer id;
  private TherapyDto therapyDto;
  private Date date;
  private List<ContraIndicationDto> contraIndications;

  public Integer getId() {
    return id;
  }

  public void setId(Integer id) {
    this.id = id;
  }

  public Date getDate() {
    return date;
  }

  public void setDate(Date date) {
    this.date = date;
  }

  public TherapyDto getTherapyDto() {
    return therapyDto;
  }

  public void setTherapyDto(TherapyDto therapyDto) {
    this.therapyDto = therapyDto;
  }

  public List<ContraIndicationDto> getContraIndications() {
    return contraIndications;
  }

  public void setContraIndications(List<ContraIndicationDto> contraIndications) {
    this.contraIndications = contraIndications;
  }
}
