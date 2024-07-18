package pack.other;

import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder

@Table(name="product")
public class ProductVo {

	@Id
	private Integer code;
	private String sang;
	private Integer su;
	private Integer dan;
}
