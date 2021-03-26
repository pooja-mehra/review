package com.example.review.schoolreview;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;

import org.hibernate.annotations.GenericGenerator;
import com.example.review.school.School;

@Entity
@Table(name = "rv_school_review")
public class ReviewSchool {
	@Id
	@GenericGenerator(
            name = "rv_school_review_id_seq",
            strategy = "org.hibernate.id.enhanced.SequenceStyleGenerator",
            parameters = {
                    @org.hibernate.annotations.Parameter(name = "sequence_name", value = "rv_school_review_id_seq"),
                    @org.hibernate.annotations.Parameter(name= "INCREMENT", value = "1"),
                    @org.hibernate.annotations.Parameter(name = "MINVALUE", value = "1"),
                    @org.hibernate.annotations.Parameter(name = "MAXVALUE", value = "100"),
                    @org.hibernate.annotations.Parameter(name = "CACHE", value = "1")
            }
    )
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "rv_school_review_id_seq")
	@Column(name="id")
	long id;
	
	@ManyToOne
    @JoinColumn(name = "school_id")
    private School schoolId;
	
	@Column(name="review_comment")
	@NotEmpty
	String reviewComment;
	
	public ReviewSchool() {}

	public ReviewSchool(long id,String reviewComment,School schoolId) {
		this.id = id;
		this.reviewComment = reviewComment;
		this.schoolId = schoolId;
	}
	
	public long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
    public String getReviewComment() {
        return reviewComment;
    }

    public void setReviewComment(String reviewComment) {
        this.reviewComment = reviewComment;
    }
    public Long getSchoolId() {
    	return schoolId.getId();
    }
    public String getSchoolName() {
    	return schoolId.getSchoolName();
    }

    public void setSchoolId(School schoolId) {
        this.schoolId = schoolId;
    }
    

}
