/**
 * package-info Class
 *
 *
 *  开发步骤：
 *  1. 在domain中增加Entity类，（领域模型）
 *      - 增加@Entity、@Table、@Data、@Accessors(chain = true)
 *      - 增加@Id及其主键生成策略，
 *      - 增加Entity类的属性和属性的约束条件
 *  2. 在repository中增加Repository接口类（数据访问接口）
 *      - 继承PlatformRepository接口类，jpaRepository、JpaSpecificationExecutor
 *
 *  3. 在service中增加Service类（服务层），
 *      - 增加@service标注为service类
 *      - 增加<Module>Repository接口类的自动注入@Autowired
 *      - 增加分页Page<Entity>，调用<module>Repository.findAll(拼接的spec条件，分页请求参数pageRequest)去获取结果集合
 *      - 增加保存save方法
 *
 *  4. 在Controller中增加Controller类（控制表现层、由spring MVC提供的）
 *     - 增加@Controller、@RequestMapping("/<module>")，并设定访问路径
 *     - 增加list方法、@RequestMapping("")，
 *          - 请求参数：pageNumber、查询条件、PAGE_SIZE
 *          - 响应数据：文件路径（<module>/list）、page、PAGE_SIZE、参数集合
 *     - 增加进入页面的方法createFrm、@GetMapping("/create")
 *          - 响应数据：文件路径(<module>/form)、Model数据
 *     - 增加新增数据的保存方法create @PostMapping("/create")
 *          - 请求参数：自动封装的Entity类，如果无法自动封装，使用request.getParameter获取
 *          - 响应数据：跳转redirect:/<module>/，RedirectAttributes
 *
 *  5. 在views文件夹中
 *      - 增加list.jsp文件，列表页面
 *      - 增加form.jsp文件，新增页面
 *      - 增加editForm.jsp文件，编辑页面
 *      - 增加Detail.jsp文件，详细页面
 *
 *
 *
 *
 *
 *
 *  -- 2020.06.04课程笔记
 *
 *   1. 在框架中同时使用JPA和Mybatis如何整合
 *      - MVC
 *      - spring 容器
 *      - ORM
 *          - JPA
 *          - Mybatis
 *   2. 整合后能够使用机构信息运行起来。
 *
 *   3. 课程作业：
 *      - 用户管理：CRUD，登录功能
 *      - 文章管理两个功能：栏目管理、文章内容管理，提交时间是：课程结束后2周内。要求，必须能够运行起来
 *
 *
 *
 *
 * @author <b>Oxidyc</b>, Copyright &#169; 2003
 * @version 1.0, 2020-05-28 14:55
 */
package cn.edu.nenu;