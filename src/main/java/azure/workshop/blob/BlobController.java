package azure.workshop.blob;

import java.io.IOException;

import org.joda.time.DateTime;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import com.azure.storage.blob.BlobClient;
import com.azure.storage.blob.BlobContainerClient;
import com.azure.storage.blob.BlobServiceClient;

@RestController
@RequestMapping("blob")
public class BlobController {

    private static final String AZURE_WORKSHOP = "azure-workshop";
    private BlobServiceClient blobServiceClient;
    private BlobRepository blobRepository;

    public BlobController(BlobRepository blobRepository, BlobServiceClient blobServiceClient) {
        this.blobRepository = blobRepository;
        this.blobServiceClient = blobServiceClient;
    }

    @PostMapping("/upload")
    public ResponseEntity<BlobDto> uploadFile(@RequestBody MultipartFile file) throws IOException {

        BlobContainerClient containerClient = blobServiceClient.getBlobContainerClient(AZURE_WORKSHOP);

        BlobClient blobClient = containerClient
                .getBlobClient(file.getOriginalFilename().concat(DateTime.now().toString()));

        blobClient.upload(file.getInputStream(), file.getSize(), true);

        Blob blob = blobRepository.save(new Blob(blobClient.getBlobUrl()));

        return ResponseEntity.ok().body(blob.geDto());
    }
}