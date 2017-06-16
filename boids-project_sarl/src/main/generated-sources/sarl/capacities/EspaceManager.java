package capacities;

import environment.Population;
import io.sarl.lang.annotation.SarlSpecification;
import io.sarl.lang.core.Capacity;
import java.util.Collection;
import java.util.UUID;
import objects.AgentBody;

/**
 * @author Rémi
 */
@SarlSpecification("0.4")
@SuppressWarnings("all")
public interface EspaceManager extends Capacity {
  /**
   * Replies the number of body in the espace
   */
  public abstract int getBodyCount();
  
  /**
   * Replies a specific body
   */
  public abstract AgentBody getAgentBody(final UUID id);
  
  /**
   * Replies all the boidsBody
   */
  public abstract Collection<AgentBody> getBoidsBody();
  
  public abstract int getEspaceWidth();
  
  public abstract int getEspaceHeight();
  
  public abstract int getEspaceDepth();
  
  public abstract AgentBody createBoid(final UUID agentId, final Population groupe);
}
