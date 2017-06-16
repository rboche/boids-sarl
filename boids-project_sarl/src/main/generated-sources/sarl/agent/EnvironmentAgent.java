package agent;

import agent.BoidsAgent;
import capacities.DefaultEspaceSkill;
import capacities.EspaceManager;
import environment.Espace;
import environment.Population;
import events.Run;
import events.force;
import events.perceive;
import io.sarl.core.DefaultContextInteractions;
import io.sarl.core.Destroy;
import io.sarl.core.Initialize;
import io.sarl.core.InnerContextAccess;
import io.sarl.core.Lifecycle;
import io.sarl.core.Logging;
import io.sarl.lang.annotation.ImportedCapacityFeature;
import io.sarl.lang.annotation.PerceptGuardEvaluator;
import io.sarl.lang.annotation.SarlSpecification;
import io.sarl.lang.annotation.SyntheticMember;
import io.sarl.lang.core.Address;
import io.sarl.lang.core.Agent;
import io.sarl.lang.core.AgentContext;
import io.sarl.lang.core.BuiltinCapacitiesProvider;
import io.sarl.lang.core.Capacity;
import io.sarl.lang.core.Scope;
import io.sarl.lang.core.Skill;
import io.sarl.util.Scopes;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.UUID;
import javafx.scene.paint.Color;
import javax.inject.Inject;
import math.Vector3f;
import objects.AgentBody;
import objects.EnvObj;
import org.eclipse.xtext.xbase.lib.ExclusiveRange;
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
public class EnvironmentAgent extends Agent {
  protected Espace espace;
  
  protected int count = 0;
  
  @SyntheticMember
  private void $behaviorUnit$Initialize$0(final Initialize occurrence) {
    Object _get = occurrence.parameters[0];
    this.espace = ((Espace) _get);
    Object _get_1 = occurrence.parameters[1];
    final Integer nbBoids = ((Integer) _get_1);
    DefaultEspaceSkill _defaultEspaceSkill = new DefaultEspaceSkill(this.espace);
    this.<DefaultEspaceSkill>$setSkill(_defaultEspaceSkill, EspaceManager.class);
    Logging _$CAPACITY_USE$IO_SARL_CORE_LOGGING$CALLER = this.$CAPACITY_USE$IO_SARL_CORE_LOGGING == null ? (this.$CAPACITY_USE$IO_SARL_CORE_LOGGING = getSkill(Logging.class)) : this.$CAPACITY_USE$IO_SARL_CORE_LOGGING;
    _$CAPACITY_USE$IO_SARL_CORE_LOGGING$CALLER.info("L\'envirronement a bien été installé");
    final List<Population> arrayGroup = new ArrayList<Population>();
    Population _population = new Population(Color.BLUE);
    arrayGroup.add(_population);
    Population _population_1 = new Population(Color.RED);
    arrayGroup.add(_population_1);
    for (final Population group : arrayGroup) {
      int _size = arrayGroup.size();
      int _divide = ((nbBoids).intValue() / _size);
      ExclusiveRange _doubleDotLessThan = new ExclusiveRange(0, _divide, true);
      for (final Integer i : _doubleDotLessThan) {
        {
          EspaceManager _$CAPACITY_USE$CAPACITIES_ESPACEMANAGER$CALLER = this.$CAPACITY_USE$CAPACITIES_ESPACEMANAGER == null ? (this.$CAPACITY_USE$CAPACITIES_ESPACEMANAGER = getSkill(EspaceManager.class)) : this.$CAPACITY_USE$CAPACITIES_ESPACEMANAGER;
          AgentBody boidsBody = _$CAPACITY_USE$CAPACITIES_ESPACEMANAGER$CALLER.createBoid(null, group);
          Vector3f position = boidsBody.getPosition();
          Lifecycle _$CAPACITY_USE$IO_SARL_CORE_LIFECYCLE$CALLER = this.$CAPACITY_USE$IO_SARL_CORE_LIFECYCLE == null ? (this.$CAPACITY_USE$IO_SARL_CORE_LIFECYCLE = getSkill(Lifecycle.class)) : this.$CAPACITY_USE$IO_SARL_CORE_LIFECYCLE;
          UUID _agentId = boidsBody.getAgentId();
          DefaultContextInteractions _$CAPACITY_USE$IO_SARL_CORE_DEFAULTCONTEXTINTERACTIONS$CALLER = this.$CAPACITY_USE$IO_SARL_CORE_DEFAULTCONTEXTINTERACTIONS == null ? (this.$CAPACITY_USE$IO_SARL_CORE_DEFAULTCONTEXTINTERACTIONS = getSkill(DefaultContextInteractions.class)) : this.$CAPACITY_USE$IO_SARL_CORE_DEFAULTCONTEXTINTERACTIONS;
          AgentContext _defaultContext = _$CAPACITY_USE$IO_SARL_CORE_DEFAULTCONTEXTINTERACTIONS$CALLER.getDefaultContext();
          _$CAPACITY_USE$IO_SARL_CORE_LIFECYCLE$CALLER.spawnInContextWithID(BoidsAgent.class, _agentId, _defaultContext, new Object[] { group, position });
          Logging _$CAPACITY_USE$IO_SARL_CORE_LOGGING$CALLER_1 = this.$CAPACITY_USE$IO_SARL_CORE_LOGGING == null ? (this.$CAPACITY_USE$IO_SARL_CORE_LOGGING = getSkill(Logging.class)) : this.$CAPACITY_USE$IO_SARL_CORE_LOGGING;
          UUID _agentId_1 = boidsBody.getAgentId();
          String _plus = ((((("L\'agent " + i) + " appartenant au groupe ") + group) + " a été créé avec l\'id ") + _agentId_1);
          String _plus_1 = (_plus + 
            "position : ");
          Vector3f _position = boidsBody.getPosition();
          String _plus_2 = (_plus_1 + _position);
          _$CAPACITY_USE$IO_SARL_CORE_LOGGING$CALLER_1.info(_plus_2);
        }
      }
    }
  }
  
  @SyntheticMember
  private void $behaviorUnit$Destroy$1(final Destroy occurrence) {
    this.<EspaceManager>clearSkill(EspaceManager.class);
  }
  
  @SyntheticMember
  private void $behaviorUnit$Run$2(final Run occurrence) {
    final Collection<AgentBody> boidsBody = this.espace.getAgentBodies();
    final Collection<EnvObj> envObj = this.espace.getAllEnvObj();
    DefaultContextInteractions _$CAPACITY_USE$IO_SARL_CORE_DEFAULTCONTEXTINTERACTIONS$CALLER = this.$CAPACITY_USE$IO_SARL_CORE_DEFAULTCONTEXTINTERACTIONS == null ? (this.$CAPACITY_USE$IO_SARL_CORE_DEFAULTCONTEXTINTERACTIONS = getSkill(DefaultContextInteractions.class)) : this.$CAPACITY_USE$IO_SARL_CORE_DEFAULTCONTEXTINTERACTIONS;
    perceive _perceive = new perceive();
    final Procedure1<perceive> _function = (perceive it) -> {
      it.objectsBody = boidsBody;
      it.objectsEnv = envObj;
    };
    perceive _doubleArrow = ObjectExtensions.<perceive>operator_doubleArrow(_perceive, _function);
    _$CAPACITY_USE$IO_SARL_CORE_DEFAULTCONTEXTINTERACTIONS$CALLER.emit(_doubleArrow);
  }
  
  @SyntheticMember
  private void $behaviorUnit$force$3(final force occurrence) {
    final UUID agentId = occurrence.agentId;
    EspaceManager _$CAPACITY_USE$CAPACITIES_ESPACEMANAGER$CALLER = this.$CAPACITY_USE$CAPACITIES_ESPACEMANAGER == null ? (this.$CAPACITY_USE$CAPACITIES_ESPACEMANAGER = getSkill(EspaceManager.class)) : this.$CAPACITY_USE$CAPACITIES_ESPACEMANAGER;
    final AgentBody boid = _$CAPACITY_USE$CAPACITIES_ESPACEMANAGER$CALLER.getAgentBody(agentId);
    final Vector3f force = occurrence.F;
    float _length = force.length();
    Population _groupe = boid.getGroupe();
    boolean _greaterThan = (_length > _groupe.maxForce);
    if (_greaterThan) {
      force.normalize();
      Population _groupe_1 = boid.getGroupe();
      force.fois(_groupe_1.maxForce);
    }
    final Vector3f acceleration = boid.getAcceleration();
    acceleration.setXYZ(force);
    final Vector3f vitesse = boid.getVitesse();
    vitesse.plus(acceleration);
    float _length_1 = vitesse.length();
    Population _groupe_2 = boid.getGroupe();
    boolean _greaterThan_1 = (_length_1 > _groupe_2.maxSpeed);
    if (_greaterThan_1) {
      vitesse.normalize();
      Population _groupe_3 = boid.getGroupe();
      vitesse.fois(_groupe_3.maxSpeed);
    }
    final Vector3f position = boid.getPosition();
    Vector3f _position = boid.getPosition();
    boid.setPrevPosition(_position);
    position.plus(vitesse);
    boid.setAcceleration(acceleration);
    boid.setVitesse(vitesse);
    boid.setPosition(position);
    this.adjustToWorld(boid);
    DefaultContextInteractions _$CAPACITY_USE$IO_SARL_CORE_DEFAULTCONTEXTINTERACTIONS$CALLER = this.$CAPACITY_USE$IO_SARL_CORE_DEFAULTCONTEXTINTERACTIONS == null ? (this.$CAPACITY_USE$IO_SARL_CORE_DEFAULTCONTEXTINTERACTIONS = getSkill(DefaultContextInteractions.class)) : this.$CAPACITY_USE$IO_SARL_CORE_DEFAULTCONTEXTINTERACTIONS;
    perceive _perceive = new perceive();
    final Procedure1<perceive> _function = (perceive it) -> {
      Collection<AgentBody> _agentBodies = this.espace.getAgentBodies();
      it.objectsBody = _agentBodies;
      Collection<EnvObj> _allEnvObj = this.espace.getAllEnvObj();
      it.objectsEnv = _allEnvObj;
    };
    perceive _doubleArrow = ObjectExtensions.<perceive>operator_doubleArrow(_perceive, _function);
    Address _source = occurrence.getSource();
    Scope<Address> _addresses = Scopes.addresses(_source);
    _$CAPACITY_USE$IO_SARL_CORE_DEFAULTCONTEXTINTERACTIONS$CALLER.emit(_doubleArrow, _addresses);
  }
  
  protected void adjustToWorld(final AgentBody body) {
    float posX = 0;
    float posY = 0;
    float posZ = 0;
    Vector3f _position = body.getPosition();
    posX = _position.x;
    Vector3f _position_1 = body.getPosition();
    posY = _position_1.y;
    Vector3f _position_2 = body.getPosition();
    posZ = _position_2.z;
    int _width = this.espace.getWidth();
    boolean _greaterThan = (posX > _width);
    if (_greaterThan) {
      posX = 0;
    }
    int _width_1 = this.espace.getWidth();
    int _multiply = ((-1) * _width_1);
    boolean _lessThan = (posX < _multiply);
    if (_lessThan) {
      int _width_2 = this.espace.getWidth();
      posX = _width_2;
    }
    int _height = this.espace.getHeight();
    boolean _greaterThan_1 = (posY > _height);
    if (_greaterThan_1) {
      posY = 0;
    }
    int _height_1 = this.espace.getHeight();
    int _multiply_1 = ((-1) * _height_1);
    boolean _lessThan_1 = (posY < _multiply_1);
    if (_lessThan_1) {
      int _height_2 = this.espace.getHeight();
      posY = _height_2;
    }
    int _depth = this.espace.getDepth();
    boolean _greaterThan_2 = (posZ > _depth);
    if (_greaterThan_2) {
      posZ = 0;
    }
    int _depth_1 = this.espace.getDepth();
    int _multiply_2 = ((-1) * _depth_1);
    boolean _lessThan_2 = (posZ < _multiply_2);
    if (_lessThan_2) {
      int _depth_2 = this.espace.getDepth();
      posZ = _depth_2;
    }
    Vector3f _vector3f = new Vector3f(posX, posY, posZ);
    body.setPosition(_vector3f);
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
  @ImportedCapacityFeature(EspaceManager.class)
  @SyntheticMember
  private transient EspaceManager $CAPACITY_USE$CAPACITIES_ESPACEMANAGER;
  
  @Inline(value = "$CAPACITY_USE$CAPACITIES_ESPACEMANAGER == null ? (this.$CAPACITY_USE$CAPACITIES_ESPACEMANAGER = getSkill(EspaceManager.class)) : this.$CAPACITY_USE$CAPACITIES_ESPACEMANAGER")
  @SyntheticMember
  @Pure
  private EspaceManager $CAPACITY_USE$CAPACITIES_ESPACEMANAGER$CALLER() {
    if (this.$CAPACITY_USE$CAPACITIES_ESPACEMANAGER == null) {
      this.$CAPACITY_USE$CAPACITIES_ESPACEMANAGER = getSkill(EspaceManager.class);
    }
    return this.$CAPACITY_USE$CAPACITIES_ESPACEMANAGER;
  }
  
  @Extension
  @ImportedCapacityFeature(InnerContextAccess.class)
  @SyntheticMember
  private transient InnerContextAccess $CAPACITY_USE$IO_SARL_CORE_INNERCONTEXTACCESS;
  
  @Inline(value = "$CAPACITY_USE$IO_SARL_CORE_INNERCONTEXTACCESS == null ? (this.$CAPACITY_USE$IO_SARL_CORE_INNERCONTEXTACCESS = getSkill(InnerContextAccess.class)) : this.$CAPACITY_USE$IO_SARL_CORE_INNERCONTEXTACCESS")
  @SyntheticMember
  @Pure
  private InnerContextAccess $CAPACITY_USE$IO_SARL_CORE_INNERCONTEXTACCESS$CALLER() {
    if (this.$CAPACITY_USE$IO_SARL_CORE_INNERCONTEXTACCESS == null) {
      this.$CAPACITY_USE$IO_SARL_CORE_INNERCONTEXTACCESS = getSkill(InnerContextAccess.class);
    }
    return this.$CAPACITY_USE$IO_SARL_CORE_INNERCONTEXTACCESS;
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
  
  @SyntheticMember
  @PerceptGuardEvaluator
  private void $guardEvaluator$force(final force occurrence, final Collection<Runnable> ___SARLlocal_runnableCollection) {
    assert occurrence != null;
    assert ___SARLlocal_runnableCollection != null;
    ___SARLlocal_runnableCollection.add(() -> $behaviorUnit$force$3(occurrence));
  }
  
  @SyntheticMember
  @PerceptGuardEvaluator
  private void $guardEvaluator$Run(final Run occurrence, final Collection<Runnable> ___SARLlocal_runnableCollection) {
    assert occurrence != null;
    assert ___SARLlocal_runnableCollection != null;
    ___SARLlocal_runnableCollection.add(() -> $behaviorUnit$Run$2(occurrence));
  }
  
  /**
   * Construct an agent.
   * @param builtinCapacityProvider - provider of the built-in capacities.
   * @param parentID - identifier of the parent. It is the identifier of the parent agent and the enclosing contect, at the same time.
   * @param agentID - identifier of the agent. If <code>null</code> the agent identifier will be computed randomly.
   */
  @Inject
  @SyntheticMember
  public EnvironmentAgent(final BuiltinCapacitiesProvider builtinCapacityProvider, final UUID parentID, final UUID agentID) {
    super(builtinCapacityProvider, parentID, agentID);
  }
  
  @SyntheticMember
  @Override
  protected <S extends Skill> S $setSkill(final S skill, final Class<? extends Capacity>... capacities) {
    this.$CAPACITY_USE$IO_SARL_CORE_LOGGING = null;
    this.$CAPACITY_USE$IO_SARL_CORE_LIFECYCLE = null;
    this.$CAPACITY_USE$IO_SARL_CORE_DEFAULTCONTEXTINTERACTIONS = null;
    this.$CAPACITY_USE$CAPACITIES_ESPACEMANAGER = null;
    this.$CAPACITY_USE$IO_SARL_CORE_INNERCONTEXTACCESS = null;
    return super.$setSkill(skill, capacities);
  }
  
  @SyntheticMember
  @Override
  protected <S extends Capacity> S clearSkill(final Class<S> capacity) {
    this.$CAPACITY_USE$IO_SARL_CORE_LOGGING = null;
    this.$CAPACITY_USE$IO_SARL_CORE_LIFECYCLE = null;
    this.$CAPACITY_USE$IO_SARL_CORE_DEFAULTCONTEXTINTERACTIONS = null;
    this.$CAPACITY_USE$CAPACITIES_ESPACEMANAGER = null;
    this.$CAPACITY_USE$IO_SARL_CORE_INNERCONTEXTACCESS = null;
    return super.clearSkill(capacity);
  }
}
