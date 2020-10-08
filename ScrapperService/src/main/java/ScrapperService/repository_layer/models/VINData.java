package ScrapperService.repository_layer.models;

import org.apache.kafka.common.protocol.types.Field;

import javax.persistence.*;
import java.util.Map;

@Entity
public class VINData {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private Long offerId;

    private String VIN;

    @ElementCollection
    private Map<String, String> parameters;

    public VINData(Long id, String VIN, Map<String, String> parameters) {
        this.offerId = id;
        this.VIN = VIN;
        this.parameters = parameters;
    }

    public Long getOfferId() {
        return offerId;
    }

    public VINData() {
    }

    public Long getId() {
        return id;
    }

    public String getVIN() {
        return VIN;
    }

    public Map<String, String> getParameters() {
        return parameters;
    }
}
