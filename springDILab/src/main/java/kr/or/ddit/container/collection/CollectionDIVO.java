package kr.or.ddit.container.collection;

import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.Set;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CollectionDIVO {
	private List<String> list;
	private String[] array;
	private Set<?> set;
	private Map<String, ?> map;
	private Properties props;
}
