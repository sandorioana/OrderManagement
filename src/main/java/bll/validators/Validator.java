package bll.validators;

public interface Validator<T> {

    /*
     * Method to be implemented by the validator classes.
     * @param t object to be validated
     * @return 0 in case of valid and -1 otherwise
     */

    int validate(T t);

}
