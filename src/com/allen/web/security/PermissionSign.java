package com.allen.web.security;

/**
 * 权限标识配置类, <br>
 * 与 permission 权限表 中的 permission_sign 字段 相对应 <br>
 * 使用:
 * 
 * <pre>
 * &#064;RequiresPermissions(value = PermissionConfig.USER_CREATE)
 * public String create() {
 *     return &quot;拥有user:create权限,能访问&quot;;
 * }
 * </pre>
 * 
 **/
public class PermissionSign {

    public static final String ORG_CREATE = "org:create";
    public static final String ORG_DELETE = "org:delete";
    public static final String ORG_MANAGE = "org:manage";
    public static final String ORG_QUERY	 = "org:query";
    public static final String ORG_UPDATE = "org:update";
    public static final String PERMISSION_CREATE = "permission:create";
    public static final String PERMISSION_DELETE = "permission:delete";
    public static final String PERMISSION_MANAGE = "permission:manage";
    public static final String PERMISSION_QUERY = "permission:query";
    public static final String PERMISSION_UPDATE = "permission:update";
    public static final String ROLE_CREATE = "role:create";
    public static final String ROLE_DELETE = "role:delete";
    public static final String ROLE_MANAGE = "role:manage";
    public static final String ROLE_QUERY = "role:query";
    public static final String ROLE_PERMISSIONASSIGNMENT = "role:permissionAssignment";
    public static final String ROLE_UPDATE = "role:update";
    /**
     * 用户新增权限 标识
     */
    public static final String USER_CREATE = "user:create";
    /**
     * 用户删除权限 标识
     */
    public static final String USER_DELETE = "user:delete";
    public static final String USER_MANAGE = "user:manage";
    public static final String USER_QUERY = "user:query";
    public static final String USER_ROLEASSIGNMENT = "user:roleAssignment";
    public static final String USER_UPDATE = "user:update";
    
    /** 审核 */
	public static final String AUDIT = "audit";
	/** 卡信息新增 */
	public static final String CARDINFO_CREATE = "cardInfo:create";
	/** 卡信息删除 */
	public static final String CARDINFO_DELETE = "cardInfo:delete";
	/** 卡信息查询 */
	public static final String CARDINFO_QUERY = "cardInfo:query";
	/** 卡信息修改 */
	public static final String CARDINFO_UPDATE = "cardInfo:update";
	/** 图样-分配诊疗项目 */
	public static final String CARDSAMPLE_ASIGNITEM = "cardSample:asignItem";
	/** 图样新增 */
	public static final String CARDSAMPLE_CREATE = "cardSample:create";
	/** 图样修改 */
	public static final String CARDSAMPLE_UPDATE = "cardSample:update";
	/** 图样删除 */
	public static final String CARDSAMPLE_DELETE = "cardSample:delete";
	/** 图样查询 */
	public static final String CARDSAMPLE_QUERY = "cardSample:query";
	/** 已提交事项查询 */
	public static final String COMMITTED_QUERY = "committed:query";
	/** 消费记录新增 */
	public static final String CONSUMPTION_CREATE = "consumption:create";
	/** 消费记录删除 */
	public static final String CONSUMPTION_DELETE = "consumption:delete";
	/** 消费记录查询 */
	public static final String CONSUMPTION_QUERY = "consumption:query";
	/** 消费记录修改 */
	public static final String CONSUMPTION_UPDATE = "consumption:update";
	/** 客户信息新增 */
	public static final String CUSTOMER_CREATE = "customer:create";
	/** 客户信息删除 */
	public static final String CUSTOMER_DELETE = "customer:delete";
	/** 客户信息查询 */
	public static final String CUSTOMER_QUERY = "customer:query";
	/** 客户信息修改 */
	public static final String CUSTOMER_UPDATE = "customer:update";
	/** DB卡信息新增 */
	public static final String DB_CREATE = "db:create";
	/** DB卡信息删除 */
	public static final String DB_DELETE = "db:delete";
	/** DB卡信息查询 */
	public static final String DB_QUERY = "db:query";
	/** DB卡信息修改 */
	public static final String DB_UPDATE = "db:update";
	/** 诊疗项目新增 */
	public static final String ITEM_CREATE = "item:create";
	/** 诊疗项目删除 */
	public static final String ITEM_DELETE = "item:delete";
	/** 诊疗项目查询 */
	public static final String ITEM_QUERY = "item:query";
	/** 诊疗项目修改 */
	public static final String ITEM_UPDATE = "item:update";   
}
