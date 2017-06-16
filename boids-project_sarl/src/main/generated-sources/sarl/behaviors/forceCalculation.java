package behaviors;

import environment.Population;
import io.sarl.lang.annotation.SarlSpecification;
import io.sarl.lang.annotation.SyntheticMember;
import io.sarl.lang.core.Address;
import io.sarl.lang.core.Event;
import java.util.Collection;
import java.util.UUID;
import math.Vector3f;
import objects.AgentBody;
import objects.EnvObj;
import org.eclipse.xtext.xbase.lib.Pure;

/**
 * @author Rémi
 */
@SarlSpecification("0.4")
@SuppressWarnings("all")
public class forceCalculation extends Event {
  public Collection<AgentBody> perceivedBoids_sep;
  
  public Collection<AgentBody> perceivedBoids_coh;
  
  public Collection<AgentBody> perceivedBoids_ali;
  
  public Collection<AgentBody> perceivedBoids_rep;
  
  public Collection<EnvObj> otherPerception;
  
  public Population groupe;
  
  public UUID agentId;
  
  public Vector3f myPosition;
  
  public Vector3f myVitesse;
  
  /**
   * Construct an event. The source of the event is unknown.
   */
  @SyntheticMember
  public forceCalculation() {
    super();
  }
  
  /**
   * Construct an event.
   * @param source - address of the agent that is emitting this event.
   */
  @SyntheticMember
  public forceCalculation(final Address source) {
    super(source);
  }
  
  @Override
  @Pure
  @SyntheticMember
  public boolean equals(final Object obj) {
    if (this == obj)
      return true;
    if (obj == null)
      return false;
    if (getClass() != obj.getClass())
      return false;
    forceCalculation other = (forceCalculation) obj;
    if (this.perceivedBoids_sep == null) {
      if (other.perceivedBoids_sep != null)
        return false;
    } else if (!this.perceivedBoids_sep.equals(other.perceivedBoids_sep))
      return false;
    if (this.perceivedBoids_coh == null) {
      if (other.perceivedBoids_coh != null)
        return false;
    } else if (!this.perceivedBoids_coh.equals(other.perceivedBoids_coh))
      return false;
    if (this.perceivedBoids_ali == null) {
      if (other.perceivedBoids_ali != null)
        return false;
    } else if (!this.perceivedBoids_ali.equals(other.perceivedBoids_ali))
      return false;
    if (this.perceivedBoids_rep == null) {
      if (other.perceivedBoids_rep != null)
        return false;
    } else if (!this.perceivedBoids_rep.equals(other.perceivedBoids_rep))
      return false;
    if (this.otherPerception == null) {
      if (other.otherPerception != null)
        return false;
    } else if (!this.otherPerception.equals(other.otherPerception))
      return false;
    if (this.groupe == null) {
      if (other.groupe != null)
        return false;
    } else if (!this.groupe.equals(other.groupe))
      return false;
    if (this.agentId == null) {
      if (other.agentId != null)
        return false;
    } else if (!this.agentId.equals(other.agentId))
      return false;
    if (this.myPosition == null) {
      if (other.myPosition != null)
        return false;
    } else if (!this.myPosition.equals(other.myPosition))
      return false;
    if (this.myVitesse == null) {
      if (other.myVitesse != null)
        return false;
    } else if (!this.myVitesse.equals(other.myVitesse))
      return false;
    return super.equals(obj);
  }
  
  @Override
  @Pure
  @SyntheticMember
  public int hashCode() {
    final int prime = 31;
    int result = super.hashCode();
    result = prime * result + ((this.perceivedBoids_sep== null) ? 0 : this.perceivedBoids_sep.hashCode());
    result = prime * result + ((this.perceivedBoids_coh== null) ? 0 : this.perceivedBoids_coh.hashCode());
    result = prime * result + ((this.perceivedBoids_ali== null) ? 0 : this.perceivedBoids_ali.hashCode());
    result = prime * result + ((this.perceivedBoids_rep== null) ? 0 : this.perceivedBoids_rep.hashCode());
    result = prime * result + ((this.otherPerception== null) ? 0 : this.otherPerception.hashCode());
    result = prime * result + ((this.groupe== null) ? 0 : this.groupe.hashCode());
    result = prime * result + ((this.agentId== null) ? 0 : this.agentId.hashCode());
    result = prime * result + ((this.myPosition== null) ? 0 : this.myPosition.hashCode());
    result = prime * result + ((this.myVitesse== null) ? 0 : this.myVitesse.hashCode());
    return result;
  }
  
  /**
   * Returns a String representation of the forceCalculation event's attributes only.
   */
  @SyntheticMember
  @Pure
  protected String attributesToString() {
    StringBuilder result = new StringBuilder(super.attributesToString());
    result.append("perceivedBoids_sep  = ").append(this.perceivedBoids_sep);
    result.append("perceivedBoids_coh  = ").append(this.perceivedBoids_coh);
    result.append("perceivedBoids_ali  = ").append(this.perceivedBoids_ali);
    result.append("perceivedBoids_rep  = ").append(this.perceivedBoids_rep);
    result.append("otherPerception  = ").append(this.otherPerception);
    result.append("groupe  = ").append(this.groupe);
    result.append("agentId  = ").append(this.agentId);
    result.append("myPosition  = ").append(this.myPosition);
    result.append("myVitesse  = ").append(this.myVitesse);
    return result.toString();
  }
  
  @SyntheticMember
  private final static long serialVersionUID = 7797338911L;
}
