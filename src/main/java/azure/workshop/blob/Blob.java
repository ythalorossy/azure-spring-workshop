package azure.workshop.blob;

import java.time.LocalDateTime;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor
public class Blob {
    
    @Id
    @GeneratedValue
    private Long id;

    private String url;

    @Temporal(TemporalType.TIMESTAMP)
    private LocalDateTime createdAt;

    public Blob(String url) {
        this.url = url;
        this.createdAt = LocalDateTime.now();
    }
    
    public BlobDto geDto() {
        return new BlobDto(this.url, this.createdAt.toString());
    }
}
