package tacos.model;

import java.util.Date;
import java.util.UUID;

import org.springframework.data.cassandra.core.cql.Ordering;
import org.springframework.data.cassandra.core.cql.PrimaryKeyType;
import org.springframework.data.cassandra.core.mapping.PrimaryKeyClass;
import org.springframework.data.cassandra.core.mapping.PrimaryKeyColumn;

import com.datastax.oss.driver.api.core.uuid.Uuids;

import lombok.Data;

@Data
@PrimaryKeyClass
public class TacoKey {

	// partition key
	@PrimaryKeyColumn(type = PrimaryKeyType.PARTITIONED)
	private UUID id = Uuids.timeBased();

	// clustering key
	@PrimaryKeyColumn(type = PrimaryKeyType.CLUSTERED, ordering = Ordering.DESCENDING)
	private Date createdAt = new Date();

}
