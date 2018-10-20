package net.javacogito.jdbcconnector.context;

/**
 * Factory to get context.
 */
public final class ContextFactory {
  private ContextFactory(){}
  private static final Context DEFAULT_CONTEXT = new EnvContext();

  /**
   * Returns default context.
   *
   * @return default context
   */
  public static Context getDefaultContext(){
    return DEFAULT_CONTEXT;
  }
}
