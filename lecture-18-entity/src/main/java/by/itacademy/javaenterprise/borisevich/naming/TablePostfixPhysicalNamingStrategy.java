package by.itacademy.javaenterprise.borisevich.naming;

import org.hibernate.boot.model.naming.Identifier;
import org.hibernate.boot.model.naming.PhysicalNamingStrategyStandardImpl;
import org.hibernate.engine.jdbc.env.spi.JdbcEnvironment;

public class TablePostfixPhysicalNamingStrategy extends PhysicalNamingStrategyStandardImpl {

    private final static String POSTFIX = "s";

    @Override
    public Identifier toPhysicalTableName(final Identifier identifier, final JdbcEnvironment jdbcEnv) {
        if (identifier == null) {
            return null;
        }

        final String newName = identifier.getText() + POSTFIX;
        return Identifier.toIdentifier(newName);
    }
}
