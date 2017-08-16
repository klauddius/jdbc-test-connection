package br.com.klauddius.jdbc.testconnection;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.jdbc.core.JdbcTemplate;

@SpringBootApplication
public class Main implements CommandLineRunner {

	@Autowired
	JdbcTemplate jdbcTemplate;

	@Value("${sqlStatement}")
	private String sqlStatement;

	@Value("${qtdChecks}")
	private Integer qtdChecks;

	private static final Logger LOG = LoggerFactory.getLogger(Main.class);

	public static void main(String[] args) {
		SpringApplication.run(Main.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		LOG.info("Executando comando no banco de dados.");

		// final String sql = "SELECT SYSDATE FROM DUAL";
		// final String sql = "SELECT CURRENT_DATE";
		final String sql = this.sqlStatement;

		do {
			final String resultado = this.jdbcTemplate.queryForObject(sql, String.class);

			LOG.info("");
			LOG.info("Resultado: " + resultado);
			LOG.info("");
			this.qtdChecks--;
		} while (this.qtdChecks > 0 || this.qtdChecks < 0);
	}

}
