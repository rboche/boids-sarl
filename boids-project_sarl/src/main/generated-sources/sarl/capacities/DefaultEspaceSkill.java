package capacities;

import capacities.EspaceManager;
import environment.Espace;
import environment.Population;
import io.sarl.lang.annotation.SarlSpecification;
import io.sarl.lang.core.Skill;
import java.util.Collection;
import java.util.UUID;
import objects.AgentBody;
import org.eclipse.xtext.xbase.lib.Pure;

@SarlSpecification("0.4")
@SuppressWarnings("all")
public class DefaultEspaceSkill extends Skill implements EspaceManager {
  protected final Espace espace;
  
  public DefaultEspaceSkill(final Espace espace) {
    this.espace = espace;
  }
  
  /**
   * Replis the number of body in the espace
   */
  @Pure
  public int getBodyCount() {
    return this.espace.getBodyCount();
  }
  
  /**
   * Replies a specific body
   */
  @Pure
  public AgentBody getAgentBody(final UUID id) {
    return this.espace.getAgentBody(id);
  }
  
  /**
   * Replies all the BoidsBody in the espace
   */
  @Pure
  public Collection<AgentBody> getBoidsBody() {
    return this.espace.getAgentBodies();
  }
  
  @Pure
  public int getEspaceWidth() {
    return this.espace.getWidth();
  }
  
  @Pure
  public int getEspaceHeight() {
    return this.espace.getHeight();
  }
  
  @Pure
  public int getEspaceDepth() {
    return this.espace.getDepth();
  }
  
  /**
   * Create a boidsBody at a random position
   */
  public AgentBody createBoid(final UUID agentId, final Population groupe) {
    return this.espace.createBody(agentId, groupe);
  }
}
