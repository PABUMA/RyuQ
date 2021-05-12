package org.pabuma.ryuq.component.terminationcondition ;

import java.util.Map;

/**
 * This interface represents classes that allow to check the termination condition of an algorithm.
 *
 * @author Antonio J. Nebro <antonio@lcc.uma.es>
 */
@FunctionalInterface
public interface TerminationCondition {
  boolean isMet(Map<String, Object> algorithmStatusData) ;
}
