package custom;

import java.io.Serializable;

import org.hibernate.engine.spi.SharedSessionContractImplementor;
import org.hibernate.id.IdentifierGenerator;

public class danhGiaGenerator implements IdentifierGenerator {

    @Override
    public Serializable generate(SharedSessionContractImplementor session, Object object) {
        // Custom logic to generate the custom identifier using database auto-increment (AUTO_INCREMENT)
        String customId = "DG " + java.util.Calendar.getInstance().getTime(); // Add your desired prefix (e.g., "TT")
        return customId;
    }
}
