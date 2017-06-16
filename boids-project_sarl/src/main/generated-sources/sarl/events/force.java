package events;

import io.sarl.lang.annotation.SarlSpecification;
import io.sarl.lang.annotation.SyntheticMember;
import io.sarl.lang.core.Address;
import io.sarl.lang.core.Event;
import java.util.UUID;
import math.Vector3f;
import org.eclipse.xtext.xbase.lib.Pure;

@SarlSpecification("0.4")
@SuppressWarnings("all")
public class force extends Event {
  public Vector3f F;
  
  public UUID agentId;
  
  /**
   * Construct an event. The source of the event is unknown.
   */
  @SyntheticMember
  public force() {
    super();
  }
  
  /**
   * Construct an event.
   * @param source - address of the agent that is emitting this event.
   */
  @SyntheticMember
  public force(final Address source) {
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
    force other = (force) obj;
    if (this.F == null) {
      if (other.F != null)
        return false;
    } else if (!this.F.equals(other.F))
      return false;
    if (this.agentId == null) {
      if (other.agentId != null)
        return false;
    } else if (!this.agentId.equals(other.agentId))
      return false;
    return super.equals(obj);
  }
  
  @Override
  @Pure
  @SyntheticMember
  public int hashCode() {
    final int prime = 31;
    int result = super.hashCode();
    result = prime * result + ((this.F== null) ? 0 : this.F.hashCode());
    result = prime * result + ((this.agentId== null) ? 0 : this.agentId.hashCode());
    return result;
  }
  
  /**
   * Returns a String representation of the force event's attributes only.
   */
  @SyntheticMember
  @Pure
  protected String attributesToString() {
    StringBuilder result = new StringBuilder(super.attributesToString());
    result.append("F  = ").append(this.F);
    result.append("agentId  = ").append(this.agentId);
    return result.toString();
  }
  
  @SyntheticMember
  private final static long serialVersionUID = 765584971L;
}
