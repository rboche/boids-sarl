package agent;

import behaviors.forceBehavior;
import behaviors.forceCalculation;
import com.google.common.base.Objects;
import environment.Population;
import events.Run;
import events.perceive;
import io.sarl.core.Behaviors;
import io.sarl.core.DefaultContextInteractions;
import io.sarl.core.Destroy;
import io.sarl.core.Initialize;
import io.sarl.core.Lifecycle;
import io.sarl.core.Logging;
import io.sarl.lang.annotation.ImportedCapacityFeature;
import io.sarl.lang.annotation.PerceptGuardEvaluator;
import io.sarl.lang.annotation.SarlSpecification;
import io.sarl.lang.annotation.SyntheticMember;
import io.sarl.lang.core.Agent;
import io.sarl.lang.core.BuiltinCapacitiesProvider;
import io.sarl.lang.core.Capacity;
import io.sarl.lang.core.Skill;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Random;
import java.util.UUID;
import javax.inject.Inject;
import math.Vector3f;
import objects.AgentBody;
import objects.EnvObj;
import org.eclipse.xtext.xbase.lib.Extension;
import org.eclipse.xtext.xbase.lib.Inline;
import org.eclipse.xtext.xbase.lib.ObjectExtensions;
import org.eclipse.xtext.xbase.lib.Procedures.Procedure1;
import org.eclipse.xtext.xbase.lib.Pure;

/**
 * @author Rémi
 */
@SarlSpecification("0.4")
@SuppressWarnings("all")
public class BoidsAgent extends Agent {
  protected forceBehavior myForceBehavior;
  
  protected final Random random = new Random();
  
  protected Vector3f position;
  
  protected Vector3f previousPosition;
  
  protected Vector3f vitesse;
  
  protected Vector3f acceleration;
  
  protected Population myGroupe;
  
  @SyntheticMember
  private void $behaviorUnit$Initialize$0(final Initialize occurrence) {
    forceBehavior _forceBehavior = new forceBehavior(this);
    this.myForceBehavior = _forceBehavior;
    Object _get = occurrence.parameters[1];
    this.position = ((Vector3f) _get);
    Object _get_1 = occurrence.parameters[0];
    this.myGroupe = ((Population) _get_1);
    Behaviors _$CAPACITY_USE$IO_SARL_CORE_BEHAVIORS$CALLER = this.$CAPACITY_USE$IO_SARL_CORE_BEHAVIORS == null ? (this.$CAPACITY_USE$IO_SARL_CORE_BEHAVIORS = getSkill(Behaviors.class)) : this.$CAPACITY_USE$IO_SARL_CORE_BEHAVIORS;
    _$CAPACITY_USE$IO_SARL_CORE_BEHAVIORS$CALLER.registerBehavior(this.myForceBehavior);
    DefaultContextInteractions _$CAPACITY_USE$IO_SARL_CORE_DEFAULTCONTEXTINTERACTIONS$CALLER = this.$CAPACITY_USE$IO_SARL_CORE_DEFAULTCONTEXTINTERACTIONS == null ? (this.$CAPACITY_USE$IO_SARL_CORE_DEFAULTCONTEXTINTERACTIONS = getSkill(DefaultContextInteractions.class)) : this.$CAPACITY_USE$IO_SARL_CORE_DEFAULTCONTEXTINTERACTIONS;
    Run _run = new Run();
    _$CAPACITY_USE$IO_SARL_CORE_DEFAULTCONTEXTINTERACTIONS$CALLER.emit(_run);
  }
  
  @SyntheticMember
  private void $behaviorUnit$Destroy$1(final Destroy occurrence) {
    Behaviors _$CAPACITY_USE$IO_SARL_CORE_BEHAVIORS$CALLER = this.$CAPACITY_USE$IO_SARL_CORE_BEHAVIORS == null ? (this.$CAPACITY_USE$IO_SARL_CORE_BEHAVIORS = getSkill(Behaviors.class)) : this.$CAPACITY_USE$IO_SARL_CORE_BEHAVIORS;
    _$CAPACITY_USE$IO_SARL_CORE_BEHAVIORS$CALLER.unregisterBehavior(this.myForceBehavior);
  }
  
  @SyntheticMember
  private void $behaviorUnit$perceive$2(final perceive occurrence) {
    Collection<AgentBody> objectsBody = occurrence.objectsBody;
    Collection<EnvObj> objectsEnv = occurrence.objectsEnv;
    final Collection<AgentBody> perceivedBoids_separation = new ArrayList<AgentBody>();
    final Collection<AgentBody> perceivedBoids_cohesion = new ArrayList<AgentBody>();
    final Collection<AgentBody> perceivedBoids_alignement = new ArrayList<AgentBody>();
    final Collection<AgentBody> perceivedBoids_repulsion = new ArrayList<AgentBody>();
    final Collection<EnvObj> perceivedEnvObj = new ArrayList<EnvObj>();
    perceivedBoids_alignement.clear();
    perceivedBoids_cohesion.clear();
    perceivedBoids_separation.clear();
    boolean _notEquals = (!Objects.equal(objectsBody, null));
    if (_notEquals) {
      for (final AgentBody obj : objectsBody) {
        {
          UUID _agentId = obj.getAgentId();
          boolean _isMe = this.isMe(_agentId);
          if (_isMe) {
            Vector3f _position = obj.getPosition();
            this.position = _position;
            Population _groupe = obj.getGroupe();
            this.myGroupe = _groupe;
            Vector3f _acceleration = obj.getAcceleration();
            this.acceleration = _acceleration;
            Vector3f _vitesse = obj.getVitesse();
            this.vitesse = _vitesse;
          }
          if (((!Objects.equal(obj, null)) && (!this.isMe(obj.getAgentId())))) {
            if ((this.visible(obj, this.myGroupe.distCohesion) && Objects.equal(this.myGroupe, obj.getGroupe()))) {
              perceivedBoids_cohesion.add(obj);
            }
            if ((this.visible(obj, this.myGroupe.distAlignement) && Objects.equal(this.myGroupe, obj.getGroupe()))) {
              perceivedBoids_alignement.add(obj);
            }
            if ((this.visible(obj, this.myGroupe.distSeparation) && Objects.equal(this.myGroupe, obj.getGroupe()))) {
              perceivedBoids_separation.add(obj);
            }
            if ((this.proche(obj, this.myGroupe.distRepulsion) && (!Objects.equal(this.myGroupe, obj.getGroupe())))) {
              perceivedBoids_repulsion.add(obj);
            }
          }
        }
      }
    }
    boolean _notEquals_1 = (!Objects.equal(objectsEnv, null));
    if (_notEquals_1) {
      for (final EnvObj obj_1 : objectsEnv) {
        if ((((((!Objects.equal(obj_1, null)) && obj_1.isWall()) && ((obj_1.getBoundsInLocal().getMinX() < this.position.x) && (obj_1.getBoundsInLocal().getMaxX() < this.position.x))) && ((obj_1.getBoundsInLocal().getMinY() < this.position.y) && (obj_1.getBoundsInLocal().getMaxY() < this.position.y))) && ((obj_1.getBoundsInLocal().getMinZ() < this.position.z) && (obj_1.getBoundsInLocal().getMaxZ() < this.position.z)))) {
          perceivedEnvObj.add(obj_1);
        }
      }
    }
    Behaviors _$CAPACITY_USE$IO_SARL_CORE_BEHAVIORS$CALLER = this.$CAPACITY_USE$IO_SARL_CORE_BEHAVIORS == null ? (this.$CAPACITY_USE$IO_SARL_CORE_BEHAVIORS = getSkill(Behaviors.class)) : this.$CAPACITY_USE$IO_SARL_CORE_BEHAVIORS;
    forceCalculation _forceCalculation = new forceCalculation();
    final Procedure1<forceCalculation> _function = (forceCalculation it) -> {
      it.perceivedBoids_sep = perceivedBoids_separation;
      it.perceivedBoids_coh = perceivedBoids_cohesion;
      it.perceivedBoids_ali = perceivedBoids_alignement;
      it.perceivedBoids_rep = perceivedBoids_repulsion;
      it.otherPerception = perceivedEnvObj;
      it.groupe = this.myGroupe;
      UUID _iD = this.getID();
      it.agentId = _iD;
      it.myPosition = this.position;
      it.myVitesse = this.vitesse;
    };
    forceCalculation _doubleArrow = ObjectExtensions.<forceCalculation>operator_doubleArrow(_forceCalculation, _function);
    _$CAPACITY_USE$IO_SARL_CORE_BEHAVIORS$CALLER.wake(_doubleArrow);
  }
  
  protected boolean visible(final AgentBody otherBoid, final double distance) {
    Vector3f _position = otherBoid.getPosition();
    Vector3f tmp = new Vector3f(_position);
    tmp.moins(this.position);
    float _length = tmp.length();
    boolean _greaterThan = (_length > distance);
    if (_greaterThan) {
      return false;
    }
    Vector3f _vitesse = otherBoid.getVitesse();
    Vector3f tmp2 = new Vector3f(_vitesse);
    tmp2.normalize();
    double _point = tmp2.point(tmp);
    boolean _lessThan = (_point < this.myGroupe.visibleAngleCos);
    if (_lessThan) {
      return false;
    }
    return true;
  }
  
  protected boolean proche(final EnvObj otherBoid, final double distance) {
    Vector3f _position = otherBoid.getPosition();
    Vector3f tmp = new Vector3f(_position);
    tmp.moins(this.position);
    float _length = tmp.length();
    boolean _greaterThan = (_length > distance);
    if (_greaterThan) {
      return false;
    }
    return true;
  }
  
  @Extension
  @ImportedCapacityFeature(Behaviors.class)
  @SyntheticMember
  private transient Behaviors $CAPACITY_USE$IO_SARL_CORE_BEHAVIORS;
  
  @Inline(value = "$CAPACITY_USE$IO_SARL_CORE_BEHAVIORS == null ? (this.$CAPACITY_USE$IO_SARL_CORE_BEHAVIORS = getSkill(Behaviors.class)) : this.$CAPACITY_USE$IO_SARL_CORE_BEHAVIORS")
  @SyntheticMember
  @Pure
  private Behaviors $CAPACITY_USE$IO_SARL_CORE_BEHAVIORS$CALLER() {
    if (this.$CAPACITY_USE$IO_SARL_CORE_BEHAVIORS == null) {
      this.$CAPACITY_USE$IO_SARL_CORE_BEHAVIORS = getSkill(Behaviors.class);
    }
    return this.$CAPACITY_USE$IO_SARL_CORE_BEHAVIORS;
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
  
  @Extension
  @ImportedCapacityFeature(Lifecycle.class)
  @SyntheticMember
  private transient Lifecycle $CAPACITY_USE$IO_SARL_CORE_LIFECYCLE;
  
  @Inline(value = "$CAPACITY_USE$IO_SARL_CORE_LIFECYCLE == null ? (this.$CAPACITY_USE$IO_SARL_CORE_LIFECYCLE = getSkill(Lifecycle.class)) : this.$CAPACITY_USE$IO_SARL_CORE_LIFECYCLE")
  @SyntheticMember
  @Pure
  private Lifecycle $CAPACITY_USE$IO_SARL_CORE_LIFECYCLE$CALLER() {
    if (this.$CAPACITY_USE$IO_SARL_CORE_LIFECYCLE == null) {
      this.$CAPACITY_USE$IO_SARL_CORE_LIFECYCLE = getSkill(Lifecycle.class);
    }
    return this.$CAPACITY_USE$IO_SARL_CORE_LIFECYCLE;
  }
  
  @SyntheticMember
  @PerceptGuardEvaluator
  private void $guardEvaluator$Initialize(final Initialize occurrence, final Collection<Runnable> ___SARLlocal_runnableCollection) {
    assert occurrence != null;
    assert ___SARLlocal_runnableCollection != null;
    ___SARLlocal_runnableCollection.add(() -> $behaviorUnit$Initialize$0(occurrence));
  }
  
  @SyntheticMember
  @PerceptGuardEvaluator
  private void $guardEvaluator$Destroy(final Destroy occurrence, final Collection<Runnable> ___SARLlocal_runnableCollection) {
    assert occurrence != null;
    assert ___SARLlocal_runnableCollection != null;
    ___SARLlocal_runnableCollection.add(() -> $behaviorUnit$Destroy$1(occurrence));
  }
  
  /**
   * on reçois les perceptions de l'envirronement
   * on calcule les forces
   * puis on renvoie les force à l'envirronement
   */
  @SyntheticMember
  @PerceptGuardEvaluator
  private void $guardEvaluator$perceive(final perceive occurrence, final Collection<Runnable> ___SARLlocal_runnableCollection) {
    assert occurrence != null;
    assert ___SARLlocal_runnableCollection != null;
    ___SARLlocal_runnableCollection.add(() -> $behaviorUnit$perceive$2(occurrence));
  }
  
  /**
   * Construct an agent.
   * @param builtinCapacityProvider - provider of the built-in capacities.
   * @param parentID - identifier of the parent. It is the identifier of the parent agent and the enclosing contect, at the same time.
   * @param agentID - identifier of the agent. If <code>null</code> the agent identifier will be computed randomly.
   */
  @Inject
  @SyntheticMember
  public BoidsAgent(final BuiltinCapacitiesProvider builtinCapacityProvider, final UUID parentID, final UUID agentID) {
    super(builtinCapacityProvider, parentID, agentID);
  }
  
  @SyntheticMember
  @Override
  protected <S extends Skill> S $setSkill(final S skill, final Class<? extends Capacity>... capacities) {
    this.$CAPACITY_USE$IO_SARL_CORE_BEHAVIORS = null;
    this.$CAPACITY_USE$IO_SARL_CORE_LOGGING = null;
    this.$CAPACITY_USE$IO_SARL_CORE_DEFAULTCONTEXTINTERACTIONS = null;
    this.$CAPACITY_USE$IO_SARL_CORE_LIFECYCLE = null;
    return super.$setSkill(skill, capacities);
  }
  
  @SyntheticMember
  @Override
  protected <S extends Capacity> S clearSkill(final Class<S> capacity) {
    this.$CAPACITY_USE$IO_SARL_CORE_BEHAVIORS = null;
    this.$CAPACITY_USE$IO_SARL_CORE_LOGGING = null;
    this.$CAPACITY_USE$IO_SARL_CORE_DEFAULTCONTEXTINTERACTIONS = null;
    this.$CAPACITY_USE$IO_SARL_CORE_LIFECYCLE = null;
    return super.clearSkill(capacity);
  }
}
