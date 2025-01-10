public class ValidadorImpl implements Validador {

    @Override
    public boolean validarCpf(String cpf) {
        cpf = cpf.replaceAll("[^0-9]", "");
        if (cpf.length() != 11 || cpf.matches("(\\d)\\1{10}")) {
            return false;
        }
        try {
            int soma = 0, digito1, digito2;
            for (int i = 0; i < 9; i++) {
                soma += (cpf.charAt(i) - '0') * (10 - i);
            }
            digito1 = 11 - (soma % 11);
            digito1 = digito1 >= 10 ? 0 : digito1;

            soma = 0;
            for (int i = 0; i < 10; i++) {
                soma += (cpf.charAt(i) - '0') * (11 - i);
            }
            digito2 = 11 - (soma % 11);
            digito2 = digito2 >= 10 ? 0 : digito2;

            return (digito1 == cpf.charAt(9) - '0') && (digito2 == cpf.charAt(10) - '0');
        } catch (Exception e) {
            return false;
        }
    }

    @Override
    public boolean validarEmail(String email) {
        String emailRegex = "^[a-zA-Z0-9_.+-]+@[a-zA-Z0-9-]+\\.[a-zA-Z0-9-.]+$";
        return email.matches(emailRegex);
    }

    @Override
    public boolean validarIsbn(String isbn) {
        isbn = isbn.replaceAll("[^0-9Xx]", "");
        if (isbn.length() != 10 && isbn.length() != 13) {
            return false;
        }
        try {
            if (isbn.length() == 13) {
                int soma = 0;
                for (int i = 0; i < 12; i++) {
                    int digito = Character.getNumericValue(isbn.charAt(i));
                    soma += (i % 2 == 0) ? digito : digito * 3;
                }
                int digitoVerificador = 10 - (soma % 10);
                digitoVerificador = digitoVerificador == 10 ? 0 : digitoVerificador;
                return digitoVerificador == Character.getNumericValue(isbn.charAt(12));
            } else if (isbn.length() == 10) {
                int soma = 0;
                for (int i = 0; i < 9; i++) {
                    soma += Character.getNumericValue(isbn.charAt(i)) * (10 - i);
                }
                char digitoVerificador = isbn.charAt(9);
                int valorVerificador = (digitoVerificador == 'X' || digitoVerificador == 'x') ? 10 : Character.getNumericValue(digitoVerificador);
                return (soma + valorVerificador) % 11 == 0;
            }
        } catch (Exception e) {
            return false;
        }
        return false;
    }
}
