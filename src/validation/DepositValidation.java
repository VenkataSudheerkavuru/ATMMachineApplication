package validation;

import constants.NoteEnum;

/**
 * validations for deposit service
 */
public class DepositValidation {

    /**
     * @return true if noteCount is negative
     */
    public boolean validateNoteCount(int noteCount) {
        return noteCount < 0;
    }

}
