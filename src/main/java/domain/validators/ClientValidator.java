package domain.validators;

import domain.Client;

public class ClientValidator implements Validator<Client>{


    @Override
    public void validate(Client entity) throws ValidatorException {

        if (entity.getName() == null) {
            throw new ValidatorException("Client name cannot be null!");
        }

        if (entity.getSerialNumber() == null) {
            throw new ValidatorException("Client serial number cannot be null!");
        }

        if (entity.getSpent() < 0) {
            throw new ValidatorException("Client name cannot be null!");
        }

        if(!entity.getName().matches("[a-zA-Z. ]+")) {
            throw new ValidatorException("Name must be made out of letters!");
        }
    }
}
