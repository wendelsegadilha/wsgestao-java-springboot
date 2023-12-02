package br.com.wendelsegadilha.gestaovendas.excecao;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


@ControllerAdvice
public class TratadorExcecaoGlobal extends ResponseEntityExceptionHandler {

    public static final String CONSTANTE_VALIDACAO_NOT_BLANK = "NotBlank";

    @ExceptionHandler(EmptyResultDataAccessException.class)
    public ResponseEntity<Object> handleEmptyResultDataAccessException(EmptyResultDataAccessException ex, WebRequest request) {
        String msgUsuario = "Recurso não encontrado";
        String msgDesenvolvedor = ex.getMessage();
        List<Erro> erros = List.of(new Erro(msgUsuario, msgDesenvolvedor));
        return handleExceptionInternal(ex, erros, new HttpHeaders(), HttpStatus.NOT_FOUND, request);
    }

    @ExceptionHandler(RegraNegocioException.class)
    public ResponseEntity<Object> handleRegraNegocioException(RegraNegocioException ex, WebRequest request) {
        String msgUsuario = ex.getMessage();
        String msgDesenvolvedor = ex.getMessage();
        List<Erro> erros = List.of(new Erro(msgUsuario, msgDesenvolvedor));
        return handleExceptionInternal(ex, erros, new HttpHeaders(), HttpStatus.BAD_REQUEST, request);
    }
    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(
            MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        List<Erro> erros = gerarListaErros(ex.getBindingResult());
        return handleExceptionInternal(ex, erros, headers, HttpStatus.BAD_REQUEST, request);
    }

    private List<Erro> gerarListaErros(BindingResult bindingResult) {
        List<Erro> erros = new ArrayList<>();
        bindingResult.getFieldErrors().forEach(
                fieldError -> {
                    String msgUsuario = tratarMensagemErroUsuario(fieldError);
                    String msgDesenvolvedor = fieldError.toString();
                    erros.add(new Erro(msgUsuario, msgDesenvolvedor));
                }
        );
        return erros;
    }

    private String tratarMensagemErroUsuario(FieldError fieldError) {
        if (fieldError.getCode().equals(CONSTANTE_VALIDACAO_NOT_BLANK)){
            return fieldError.getDefaultMessage().concat(" é obrigatório.");
        }
        if (fieldError.getCode().equals("Length")){
            Object[] arguments = fieldError.getArguments();
            return fieldError.getDefaultMessage().concat(String.format(" deve ter entre %s e %s caracteres.", arguments[2], arguments[1]));
        }
        return fieldError.toString();
    }
}
