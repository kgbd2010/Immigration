package co.laomag.immigration.entity;

import lombok.Data;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.io.Serializable;

/**
 * 项目部分代码
 */
@Entity
@Table(name = "nd_programs")
@Data
public class Programs implements Serializable{
	private static final long serialVersionUID = -2729780590855592618L;
	
	@Id
	@GenericGenerator(name = "idGenerator", strategy = "uuid")
	@GeneratedValue(generator = "idGenerator")
	private String programId;
	
	@Column(name = "program_title")
	private String programTitle;//项目标题
	
	@Column(name = "program_content")
	private String programContent;//项目内容
	
	@Column(name = "program_comment")
	private String programComment;//项目备注
	
	@Column(name = "program_status")
	private Integer programStatus;//1 待发布，2已发布
	
}
