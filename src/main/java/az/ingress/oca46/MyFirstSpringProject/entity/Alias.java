package az.ingress.oca46.MyFirstSpringProject.entity;

import az.ingress.oca46.MyFirstSpringProject.dto.AliasType;
import az.ingress.oca46.MyFirstSpringProject.dto.Status;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Data
@Table(name = "Alias_Info")
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Alias {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long aliasId;

    @Enumerated(EnumType.STRING)
    private AliasType aliasType;

    @ManyToOne(fetch = FetchType.LAZY)
    @JsonIgnore
    Customer customer;

    @JsonProperty("customerId")
    public long getCustomerId(){
        return customer.getCustomerId();
    }

    private String aliasValue;
    private Status status;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
