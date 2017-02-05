package io.github.sgreben.rc;

import io.github.sgreben.rc.expressions.Expression;
import io.github.sgreben.rc.parser.ExpressionParser;
import io.github.sgreben.rc.types.EnumType;
import org.junit.Before;
import org.junit.Test;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.core.IsEqual.equalTo;
import static org.junit.Assert.assertThat;

public class AnExprParserShould {
    private ExpressionParser parser;
    private Context context = new Context();

    @Before
    public void setUp() {
        parser = new ExpressionParser(context, Collections.emptyMap());
    }

    @Test
    public void parse_true() {
        assertThat(parser.parse("true"), is(equalTo(context.buildExpression().True())));
    }

    @Test
    public void parse_false() {
        assertThat(parser.parse("false"), is(equalTo(context.buildExpression().False())));
    }

    @Test
    public void parse_an_integer() {
        assertThat(parser.parse("12345"), is(equalTo(context.buildValue().integer(12345))));
    }

    @Test
    public void parse_an_addition() {
        Expression expected = context.buildExpression().Add(context.buildValue().integer(1), context.buildValue().integer(2));
        assertThat(parser.parse("1+2"), is(equalTo(expected)));
    }

    @Test
    public void parse_an_addition_and_multiplication_without_parens() {
        Expression expected = context.buildExpression().Add(context.buildExpression().Mul(context.buildValue().integer(1), context.buildValue().integer(3)), context.buildValue().integer(2));
        assertThat(parser.parse("1 * 3 + 2"), is(equalTo(expected)));
    }

    @Test
    public void parse_an_addition_and_multiplication_with_parens() {
        Expression expected = context.buildExpression().Mul(context.buildValue().integer(1), context.buildExpression().Add(context.buildValue().integer(3), context.buildValue().integer(2)));
        assertThat(parser.parse("1 * (3 + 2)"), is(equalTo(expected)));
    }

    @Test
    public void parse_a_linear_constraint() {
        Expression expected =
                context.buildExpression().LessOrEqual(
                        context.buildExpression().Mul(context.buildValue().integer(1), context.buildExpression().Add(context.buildValue().integer(3), context.buildValue().integer(2))),
                        context.buildValue().integer(123)
                );
        assertThat(parser.parse("1 * (3 + 2) <= 123"), is(equalTo(expected)));
    }

    @Test
    public void parse_a_conjunction_of_linear_constraints() {
        Expression expected =
                context.buildExpression().And(
                        context.buildExpression().GreaterOrEqual(
                                context.buildExpression().Mul(context.buildValue().integer(1), context.buildExpression().Add(context.buildValue().integer(3), context.buildValue().integer(2))),
                                context.buildValue().integer(123)
                        ),
                        context.buildExpression().LessOrEqual(
                                context.buildExpression().Mul(context.buildValue().integer(1), context.buildExpression().Add(context.buildValue().integer(3), context.buildValue().integer(2))),
                                context.buildValue().integer(456)
                        )
                );
        assertThat(parser.parse("1 * (3 + 2) >= 123 && 1 * (3 + 2) <= 456"), is(equalTo(expected)));
    }

    @Test
    public void parse_a_disjunction_of_linear_constraints() {
        Expression expected =
                context.buildExpression().Or(
                        context.buildExpression().LessOrEqual(
                                context.buildExpression().Mul(
                                        context.buildValue().integer(1),
                                                context.buildExpression().ITE(
                                                        context.buildExpression().Greater(context.buildValue().integer(1), context.buildValue().integer(0)),
                                                        context.buildExpression().Add(context.buildValue().integer(3), context.buildValue().integer(2)),
                                                        context.buildValue().integer(1)
                                                )
                                ),
                                context.buildValue().integer(123)
                        ),
                        context.buildExpression().Equal(
                                context.buildExpression().Mul(context.buildValue().integer(1), context.buildExpression().Add(context.buildValue().integer(3), context.buildValue().integer(2))),
                                context.buildValue().integer(456)
                        )
                );
        assertThat(parser.parse("1 * (if 1 > 0 then 3 + 2 else 1) <= 123 || 1 * (3 + 2) = 456"), is(equalTo(expected)));
    }

    @Test
    public void parse_a_disjunction_of_a_linear_constraint_and_a_negation_of_a_linear_constraint() {
        Expression expected =
                context.buildExpression().Or(
                        context.buildExpression().LessOrEqual(
                                context.buildExpression().Mul(
                                        context.buildExpression().Power(context.buildValue().real(1.2), context.buildValue().integer(2)),
                                        context.buildExpression().Div(
                                                context.buildExpression().Sub(context.buildValue().integer(3), context.buildValue().integer(2)),
                                                context.buildValue().integer(2)
                                        )
                                ),
                                context.buildValue().integer(123)
                        ),
                        context.buildExpression().Not(
                                context.buildExpression().Less(
                                        context.buildExpression().Mul(context.buildValue().integer(1), context.buildExpression().Add(context.buildValue().integer(3), context.buildValue().integer(2))),
                                        context.buildValue().integer(456)
                                )
                        )
                );
        assertThat(parser.parse("1.2^2 * ((3 - 2) / 2) <= 123 || ! (1 * (3 + 2) < 456)"), is(equalTo(expected)));
    }

    @Test(expected = IllegalArgumentException.class)
    public void parse_an_undefined_identifier() {
        parser = new ExpressionParser(context, Collections.emptyMap());
        parser.parse("myIdentifier");
    }

    @Test
    public void parse_a_variable_identifier() {
        Map<String, Variable> variableMap = new HashMap<>();
        EnumType enumType = context.buildType().enumeration().withName("myType").withValue("DUMMY").build();
        Variable variable = context.buildExpression().variable("myIdentifier").ofType(enumType);
        variableMap.put(variable.getName(), variable);
        parser = new ExpressionParser(context, variableMap);
        assertThat(parser.parse("myIdentifier"), is(equalTo(variable)));
    }

    @Test
    public void parse_an_enum_value_identifier() {
        Map<String, Variable> variableMap = new HashMap<>();
        EnumType enumType = context.buildType().enumeration().withName("myType").withValue("DUMMY").build();
        Variable variable = context.buildExpression().variable("myIdentifier").ofType(enumType);
        variableMap.put(variable.getName(), variable);
        parser = new ExpressionParser(context, variableMap);
        assertThat(parser.parse("DUMMY"), is(equalTo(enumType.getValue("DUMMY"))));
    }

}
