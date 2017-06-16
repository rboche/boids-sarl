package behaviors;

import behaviors.forceCalculation;
import com.google.common.base.Objects;
import environment.Population;
import events.force;
import io.sarl.core.DefaultContextInteractions;
import io.sarl.core.Logging;
import io.sarl.lang.annotation.ImportedCapacityFeature;
import io.sarl.lang.annotation.PerceptGuardEvaluator;
import io.sarl.lang.annotation.SarlSpecification;
import io.sarl.lang.annotation.SyntheticMember;
import io.sarl.lang.core.Agent;
import io.sarl.lang.core.Behavior;
import io.sarl.lang.core.Capacity;
import io.sarl.lang.core.Skill;
import java.util.Collection;
import java.util.UUID;
import javafx.geometry.Bounds;
import math.Vector3f;
import objects.AgentBody;
import objects.EnvObj;
import org.eclipse.xtext.xbase.lib.Extension;
import org.eclipse.xtext.xbase.lib.Inline;
import org.eclipse.xtext.xbase.lib.ObjectExtensions;
import org.eclipse.xtext.xbase.lib.Procedures.Procedure1;
import org.eclipse.xtext.xbase.lib.Pure;

@SarlSpecification("0.4")
@SuppressWarnings("all")
public class forceBehavior extends Behavior {
  protected Population groupe;
  
  protected UUID agentId;
  
  @SyntheticMember
  private void $behaviorUnit$forceCalculation$0(final forceCalculation occurrence) {
    Vector3f force_separation = new Vector3f(0, 0, 0);
    Vector3f force_cohesion = new Vector3f(0, 0, 0);
    Vector3f force_alignement = new Vector3f(0, 0, 0);
    Vector3f force_repulsion = new Vector3f(0, 0, 0);
    Vector3f otherForce = new Vector3f(0, 0, 0);
    this.groupe = occurrence.groupe;
    final Vector3f influence = new Vector3f(0, 0, 0);
    if (occurrence.groupe.cohesionOn) {
      Vector3f _cohesion = this.cohesion(occurrence.perceivedBoids_coh, occurrence.myPosition);
      force_cohesion = _cohesion;
      force_cohesion.fois(this.groupe.cohesionForce);
      influence.plus(force_cohesion);
    }
    if (occurrence.groupe.alignementOn) {
      Vector3f _alignement = this.alignement(occurrence.perceivedBoids_ali, occurrence.myPosition);
      force_alignement = _alignement;
      force_alignement.fois(this.groupe.alignementForce);
      influence.plus(force_alignement);
    }
    if (occurrence.groupe.repulsionOn) {
      Vector3f _repulsion = this.repulsion(occurrence.perceivedBoids_rep, occurrence.myPosition);
      force_repulsion = _repulsion;
      force_repulsion.fois(this.groupe.repulsionForce);
      influence.plus(force_repulsion);
    }
    if (occurrence.groupe.separationOn) {
      Vector3f _separation = this.separation(occurrence.perceivedBoids_sep, occurrence.myPosition);
      force_separation = _separation;
      force_separation.fois(this.groupe.separationForce);
      influence.plus(force_separation);
    }
    Vector3f _otherForce = this.otherForce(occurrence.agentId, occurrence.otherPerception, occurrence.myPosition, occurrence.myVitesse);
    otherForce = _otherForce;
    influence.plus(otherForce);
    float _length = influence.length();
    boolean _greaterThan = (_length > occurrence.groupe.maxForce);
    if (_greaterThan) {
      influence.normalize();
      influence.fois(occurrence.groupe.maxForce);
    }
    influence.fois((1 / occurrence.groupe.masse));
    DefaultContextInteractions _$CAPACITY_USE$IO_SARL_CORE_DEFAULTCONTEXTINTERACTIONS$CALLER = this.$CAPACITY_USE$IO_SARL_CORE_DEFAULTCONTEXTINTERACTIONS == null ? (this.$CAPACITY_USE$IO_SARL_CORE_DEFAULTCONTEXTINTERACTIONS = getSkill(DefaultContextInteractions.class)) : this.$CAPACITY_USE$IO_SARL_CORE_DEFAULTCONTEXTINTERACTIONS;
    force _force = new force();
    final Procedure1<force> _function = (force it) -> {
      it.F = influence;
      it.agentId = occurrence.agentId;
    };
    force _doubleArrow = ObjectExtensions.<force>operator_doubleArrow(_force, _function);
    _$CAPACITY_USE$IO_SARL_CORE_DEFAULTCONTEXTINTERACTIONS$CALLER.emit(_doubleArrow);
  }
  
  protected Vector3f separation(final Collection<AgentBody> perceivedBoids, final Vector3f position) {
    Vector3f tmp = new Vector3f(0, 0, 0);
    Vector3f force = new Vector3f(0, 0, 0);
    double len = 0;
    boolean _notEquals = (!Objects.equal(perceivedBoids, null));
    if (_notEquals) {
      for (final AgentBody otherBoids : perceivedBoids) {
        {
          tmp.setXYZ(position);
          Vector3f _position = otherBoids.getPosition();
          tmp.moins(_position);
          float _length = tmp.length();
          len = _length;
          tmp.fois((1 / (len * len)));
          force.plus(tmp);
        }
      }
    }
    return force;
  }
  
  protected Vector3f cohesion(final Collection<AgentBody> perceivedBoids, final Vector3f position) {
    int nbBoids = 0;
    Vector3f force = new Vector3f(0, 0, 0);
    boolean _notEquals = (!Objects.equal(perceivedBoids, null));
    if (_notEquals) {
      for (final AgentBody otherBoids : perceivedBoids) {
        {
          Vector3f _position = otherBoids.getPosition();
          force.plus(_position);
          nbBoids++;
        }
      }
      if ((nbBoids > 0)) {
        force.fois((1 / nbBoids));
        force.moins(position);
      }
    }
    return force;
  }
  
  protected Vector3f alignement(final Collection<AgentBody> perceivedBoids, final Vector3f position) {
    int nbBoids = 0;
    Vector3f force = new Vector3f(0, 0, 0);
    Vector3f tmp = new Vector3f();
    boolean _notEquals = (!Objects.equal(perceivedBoids, null));
    if (_notEquals) {
      for (final AgentBody otherBoids : perceivedBoids) {
        {
          Vector3f _vitesse = otherBoids.getVitesse();
          tmp.plus(_vitesse);
          float _length = tmp.length();
          float _divide = (1 / _length);
          tmp.fois(_divide);
          force.plus(tmp);
          nbBoids++;
        }
      }
      if ((nbBoids > 0)) {
        force.fois((1 / nbBoids));
      }
    }
    return force;
  }
  
  protected Vector3f repulsion(final Collection<AgentBody> perceivedBoids, final Vector3f position) {
    Vector3f force = new Vector3f(0, 0, 0);
    Vector3f tmp = new Vector3f();
    double len = 0;
    boolean _notEquals = (!Objects.equal(perceivedBoids, null));
    if (_notEquals) {
      for (final AgentBody otherBoid : perceivedBoids) {
        {
          tmp.setXYZ(position);
          Vector3f _position = otherBoid.getPosition();
          tmp.moins(_position);
          float _length = tmp.length();
          len = _length;
          tmp.fois((1 / (len * len)));
          force.plus(tmp);
        }
      }
    }
    return force;
  }
  
  protected Vector3f otherForce(final UUID agentId, final Collection<EnvObj> perceivedObj, final Vector3f position, final Vector3f vitesse) {
    Vector3f force = new Vector3f(0, 0, 0);
    Vector3f tmp = new Vector3f(0, 0, 0);
    Vector3f collisionVector = new Vector3f(vitesse);
    boolean _notEquals = (!Objects.equal(perceivedObj, null));
    if (_notEquals) {
      for (final EnvObj obj : perceivedObj) {
        boolean _isWall = obj.isWall();
        if (_isWall) {
          Bounds objPos = obj.getBoundsInLocal();
          boolean _contains = objPos.contains((position.x - this.groupe.distObstacle), (position.y - this.groupe.distObstacle), 
            (position.z - this.groupe.distObstacle));
          if (_contains) {
            force = collisionVector;
            Vector3f _position = obj.getPosition();
            force.moins(_position);
            force.normalize();
          }
        }
      }
    }
    return force;
  }
  
  @Extension
  @ImportedCapacityFeature(DefaultContextInteractions.class)
  @SyntheticMember
  private transient DefaultContextInteractions $CAPACITY_USE$IO_SARL_CORE_DEFAULTCONTEXTINTERACTIONS;
  
  @Inline(value = "$CAPACITY_USE$IO_SARL_CORE_DEFAULTCONTEXTINTERACTIONS == null ? (this.$CAPACITY_USE$IO_SARL_CORE_DEFAULTCONTEXTINTERACTIONS = getSkill(DefaultContextInteractions.class)) : this.$CAPACITY_USE$IO_SARL_CORE_DEFAULTCONTEXTINTERACTIONS")
  @SyntheticMember
  @Pure
  private DefaultContextInteractions $CAPACITY_USE$IO_SARL_CORE_DEFAULTCONTEXTINTERACTIONS$CALLER() {
    if (this.$CAPACITY_USE$IO_SARL_CORE_DEFAULTCONTEXTINTERACTIONS == null) {
      this.$CAPACITY_USE$IO_SARL_CORE_DEFAULTCONTEXTINTERACTIONS = getSkill(DefaultContextInteractions.class);
    }
    return this.$CAPACITY_USE$IO_SARL_CORE_DEFAULTCONTEXTINTERACTIONS;
  }
  
  @Extension
  @ImportedCapacityFeature(Logging.class)
  @SyntheticMember
  private transient Logging $CAPACITY_USE$IO_SARL_CORE_LOGGING;
  
  @Inline(value = "$CAPACITY_USE$IO_SARL_CORE_LOGGING == null ? (this.$CAPACITY_USE$IO_SARL_CORE_LOGGING = getSkill(Logging.class)) : this.$CAPACITY_USE$IO_SARL_CORE_LOGGING")
  @SyntheticMember
  @Pure
  private Logging $CAPACITY_USE$IO_SARL_CORE_LOGGING$CALLER() {
    if (this.$CAPACITY_USE$IO_SARL_CORE_LOGGING == null) {
      this.$CAPACITY_USE$IO_SARL_CORE_LOGGING = getSkill(Logging.class);
    }
    return this.$CAPACITY_USE$IO_SARL_CORE_LOGGING;
  }
  
  @SyntheticMember
  @PerceptGuardEvaluator
  private void $guardEvaluator$forceCalculation(final forceCalculation occurrence, final Collection<Runnable> ___SARLlocal_runnableCollection) {
    assert occurrence != null;
    assert ___SARLlocal_runnableCollection != null;
    ___SARLlocal_runnableCollection.add(() -> $behaviorUnit$forceCalculation$0(occurrence));
  }
  
  /**
   * Construct a behavior.
   * @param owner - reference to the agent that is owning this behavior.
   */
  @SyntheticMember
  public forceBehavior(final Agent owner) {
    super(owner);
  }
  
  @SyntheticMember
  @Override
  protected <S extends Skill> S $setSkill(final S skill, final Class<? extends Capacity>... capacities) {
    this.$CAPACITY_USE$IO_SARL_CORE_LOGGING = null;
    this.$CAPACITY_USE$IO_SARL_CORE_DEFAULTCONTEXTINTERACTIONS = null;
    return super.$setSkill(skill, capacities);
  }
  
  @SyntheticMember
  @Override
  protected <S extends Capacity> S clearSkill(final Class<S> capacity) {
    this.$CAPACITY_USE$IO_SARL_CORE_LOGGING = null;
    this.$CAPACITY_USE$IO_SARL_CORE_DEFAULTCONTEXTINTERACTIONS = null;
    return super.clearSkill(capacity);
  }
}
