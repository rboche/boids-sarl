package events;

import io.sarl.lang.annotation.SarlSpecification;
import io.sarl.lang.annotation.SyntheticMember;
import io.sarl.lang.core.Address;
import io.sarl.lang.core.Event;
import java.util.Collection;
import objects.AgentBody;
import objects.EnvObj;
import org.eclipse.xtext.xbase.lib.Pure;

@SarlSpecification("0.4")
@SuppressWarnings("all")
public class perceive extends Event {
  public Collection<AgentBody> objectsBody;
  
  public Collection<EnvObj> objectsEnv;
  
  /**
   * Construct an event. The source of the event is unknown.
   */
  @SyntheticMember
  public perceive() {
    super();
  }
  
  /**
   * Construct an event.
   * @param source - address of the agent that is emitting this event.
   */
  @SyntheticMember
  public perceive(final Address source) {
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
    perceive other = (perceive) obj;
    if (this.objectsBody == null) {
      if (other.objectsBody != null)
        return false;
    } else if (!this.objectsBody.equals(other.objectsBody))
      return false;
    if (this.objectsEnv == null) {
      if (other.objectsEnv != null)
        return false;
    } else if (!this.objectsEnv.equals(other.objectsEnv))
      return false;
    return super.equals(obj);
  }
  
  @Override
  @Pure
  @SyntheticMember
  public int hashCode() {
    final int prime = 31;
    int result = super.hashCode();
    result = prime * result + ((this.objectsBody== null) ? 0 : this.objectsBody.hashCode());
    result = prime * result + ((this.objectsEnv== null) ? 0 : this.objectsEnv.hashCode());
    return result;
  }
  
  /**
   * Returns a String representation of the perceive event's attributes only.
   */
  @SyntheticMember
  @Pure
  protected String attributesToString() {
    StringBuilder result = new StringBuilder(super.attributesToString());
    result.append("objectsBody  = ").append(this.objectsBody);
    result.append("objectsEnv  = ").append(this.objectsEnv);
    return result.toString();
  }
  
  @SyntheticMember
  private final static long serialVersionUID = 1933486312L;
}
