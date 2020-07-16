package rental_private;

import javax.persistence.*;
import org.springframework.beans.BeanUtils;
import rental_private.external.Call;
import rental_private.external.CallService;

import java.util.List;

@Entity
@Table(name="Satisfaction_table")
public class Satisfaction {

    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Long id;
    private Long checkId;
    private Integer score;

    @PrePersist
    public void onPrePersist(){


        Call call = new Call();
        call.setId(getId());
        call.setRequestYn("Y");
        SatisfactionApplication.applicationContext.getBean(CallService.class)
            .callSurvey(call);

        SatisfactionSurveyed satisfactionSurveyed = new SatisfactionSurveyed();
        BeanUtils.copyProperties(this, satisfactionSurveyed);
        satisfactionSurveyed.publishAfterCommit();


    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
    public Long getCheckId() {
        return checkId;
    }

    public void setCheckId(Long checkId) {
        this.checkId = checkId;
    }
    public Integer getScore() {
        return score;
    }

    public void setScore(Integer score) {
        this.score = score;
    }




}
