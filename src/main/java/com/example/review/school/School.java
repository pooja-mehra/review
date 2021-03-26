package com.example.review.school;

import java.util.HashSet;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;
import org.hibernate.annotations.GenericGenerator;
import com.example.review.schoolreview.ReviewSchool;

@Entity
@Table(name="rv_school")
public class School {
	
	@Id
	@GenericGenerator(
            name = "rv_school_id_seq",
            strategy = "org.hibernate.id.enhanced.SequenceStyleGenerator",
            parameters = {
                    @org.hibernate.annotations.Parameter(name = "sequence_name", value = "rv_school_id_seq"),
                    @org.hibernate.annotations.Parameter(name= "INCREMENT", value = "1"),
                    @org.hibernate.annotations.Parameter(name = "MINVALUE", value = "1"),
                    @org.hibernate.annotations.Parameter(name = "MAXVALUE", value = "50"),
                    @org.hibernate.annotations.Parameter(name = "CACHE", value = "1")
            }
    )
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "rv_school_id_seq")
	@Column(name ="id")
	long id;
	
	@Column(name ="school_name")
	@NotEmpty
	String schoolName;
	
	@Column(name ="school_level")
	@NotEmpty
	String schoolLevel;
	
	@Column(name ="school_city")
	@NotEmpty
	String schoolCity;
	
	 @OneToMany(fetch = FetchType.LAZY,cascade = CascadeType.ALL, orphanRemoval = true,
	            mappedBy = "schoolId")
	 private Set<ReviewSchool> schoolReview = new HashSet<ReviewSchool>();
	    
	
	public School() {}
	public School(long id, String schoolName, String schoolLevel, String schoolCity) {
		this.id = id;
		this.schoolName = schoolName;
		this.schoolLevel = schoolLevel;
		this.schoolCity = schoolCity;
	
	}
	
	
	public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }
    
    
    public String getSchoolName() {
        return schoolName;
    }

    public void setSchoolName(String schoolName) {
        this.schoolName = schoolName;
    }
    
    
    public String getSchoolLevel() {
        return schoolLevel;
    }
    public void SetsetSchoolLevel(String schoolLevel) {
        this.schoolLevel = schoolLevel;
    }
    public Set<ReviewSchool> getSchoolReview() {
        return schoolReview;
    }
    public void setSchoolReview(Set<ReviewSchool> schoolReview) {
        this.schoolReview = schoolReview;
    }
   
    public String getSchoolCity() {
        return schoolCity;
    }

    public void setSchoolCity(String schoolCity) {
        this.schoolCity = schoolCity;
    }
    
}
