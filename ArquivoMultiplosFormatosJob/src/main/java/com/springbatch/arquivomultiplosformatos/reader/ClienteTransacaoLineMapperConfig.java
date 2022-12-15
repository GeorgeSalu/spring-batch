package com.springbatch.arquivomultiplosformatos.reader;

import java.util.HashMap;
import java.util.Map;

import org.springframework.batch.item.file.mapping.BeanWrapperFieldSetMapper;
import org.springframework.batch.item.file.mapping.FieldSetMapper;
import org.springframework.batch.item.file.mapping.PatternMatchingCompositeLineMapper;
import org.springframework.batch.item.file.transform.DelimitedLineTokenizer;
import org.springframework.batch.item.file.transform.LineTokenizer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.springbatch.arquivomultiplosformatos.dominio.Cliente;
import com.springbatch.arquivomultiplosformatos.dominio.Transacao;

@Configuration
public class ClienteTransacaoLineMapperConfig {

	@Bean
	public PatternMatchingCompositeLineMapper lineMapper() {
		PatternMatchingCompositeLineMapper lineMapper = new PatternMatchingCompositeLineMapper<>();
		lineMapper.setTokenizers(tokenizers());
		lineMapper.setFieldSetMappers(fieldSetMappers());
		return lineMapper;
	}

	private Map<String, FieldSetMapper> fieldSetMappers() {
		Map<String, FieldSetMapper> fieldSetMapper = new HashMap<>();
		fieldSetMapper.put("0*", fielSetMapper(Cliente.class));
		fieldSetMapper.put("1*", fielSetMapper(Transacao.class));
		return fieldSetMapper;
	}

	private FieldSetMapper fielSetMapper(Class classe) {
		BeanWrapperFieldSetMapper fieldSetMapper = new BeanWrapperFieldSetMapper<>();
		fieldSetMapper.setTargetType(classe);
		return fieldSetMapper;
	}

	private Map<String, LineTokenizer> tokenizers() {
		Map<String, LineTokenizer> tokenizers = new HashMap<>();
		tokenizers.put("0*", clienteLineTokenizer());
		tokenizers.put("1*", transacaoLineTokenizer());
		return tokenizers;
	}

	private LineTokenizer clienteLineTokenizer() {
		DelimitedLineTokenizer lineTokenizer = new DelimitedLineTokenizer();
		lineTokenizer.setNames("nome", "sobrenome", "idade", "email");
		lineTokenizer.setIncludedFields(1,2,3,4);
		return lineTokenizer;
	}
	
	private LineTokenizer transacaoLineTokenizer() {
		DelimitedLineTokenizer lineTokenizer = new DelimitedLineTokenizer();
		lineTokenizer.setNames("id", "descricao", "valor");
		lineTokenizer.setIncludedFields(1,2,3);
		return lineTokenizer;
	}
	
}
