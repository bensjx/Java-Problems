import java.util.*;

public class Ivle {

    // define your own attributes, constructor, and methods here


    public void Register(Student std, Module module){
        String stdname = std.getName();
        ArrayList<Module> stdmodules = std.getModules();
        String modulecode = module.getCode();
        ArrayList<Student> modulestd = module.getStudent();
        if (stdmodules.contains(module)){
            System.out.println(stdname+" is already registered into "+
                    modulecode);
        } else if (module.getVacancy() == 0){
            System.out.println(modulecode+" is full");
        } else{
            module.deductvac();
            modulestd.add(std);
            std.addmc(module.getmc());
            stdmodules.add(module);
            System.out.println(stdname+" successfully registered into "+
                    modulecode);
        }
    }

    public void Remove(Student std, Module module){
        ArrayList<Module> stdmodules = std.getModules();
        ArrayList<Student> modulestd = module.getStudent();
        if(!modulestd.contains(std)){
            System.out.println(std.getName()+" is not registered into "+
                    module.getCode());
        } else{
            module.addvac();
            modulestd.remove(std);
            std.deductmc(module.getmc());
            stdmodules.remove(module);
            System.out.println(std.getName()+" has been removed from "+
                    module.getCode());
        }
    }

    public void Details(Student std){
        ArrayList<Module> modules = std.getModules();
        if (modules.isEmpty()){
            System.out.println(std.getName()+" has no modules");
        } else{
            int total = 0;
            for (int i = 0; i<modules.size();i++){
                total += (modules.get(i)).getmc();
            }
            ArrayList<String> temp = new ArrayList<String>();
            for (int y = 0; y < modules.size(); y++){
                temp.add(modules.get(y).getCode());
            }
            Collections.sort(temp);
            System.out.print(total);
            for (int x = 0; x<temp.size();x++){
                System.out.print(" " + (temp.get(x)));
            }
            System.out.println();
        }
    }

    public void Total(ArrayList<Student> listofstd){
        int count = 0;
        if (listofstd.isEmpty()){
            System.out.println("No students registered for modules");
            return;
        }
        for (int i = 0; i<listofstd.size(); i++){
            if(!((listofstd.get(i)).getModules()).isEmpty()){
                count +=1;
            }
        }
        if (count == 0){
            System.out.println("No students registered for "
                    + "modules");
        }
        else{
            System.out.println(count);
        }
    }

    public void Highest(ArrayList<Student> listofstd){
        int high = 0;
        ArrayList<String> highnames = new ArrayList<String>();
        for (int i = 0; i < listofstd.size(); i++){
            if ((listofstd.get(i)).getmc()>=high){
                high = (listofstd.get(i)).getmc();
            }
        }
        if (high == 0){
            System.out.println("No students registered for modules");
            return;
        }
        for (int x = 0; x < listofstd.size();x++){
            if (listofstd.get(x).getmc() == high){
                highnames.add(listofstd.get(x).getName());
            }
        }
        System.out.print(high);
        Collections.sort(highnames);
        for (int y = 0; y<highnames.size();y++){
            System.out.print(" " + highnames.get(y));
        }
        System.out.println();
    }

    private void run(Scanner sc,ArrayList<Module>
            _modules,ArrayList<Student> _listofstd) {
        String qtype = sc.next();
        if (qtype.equals("total")){
            Total(_listofstd);
        }else if(qtype.equals("register")){
            String name = sc.next();
            String module = sc.next();
            Student temp = new Student(name,0,new ArrayList<Module>());
            int indexmodule = 0;
            for (int y = 0; y < _modules.size(); y++){
                if (_modules.get(y).getCode().equals(module)){
                    indexmodule = y;
                }
            }
            for (int i = 0; i < _listofstd.size(); i++){
                if (_listofstd.get(i).getName().equals(name)){
                    Register(_listofstd.get(i),_modules.get(indexmodule));
                    return;
                }
            }
            Student std = new Student(name,0,new ArrayList<Module>());
            _listofstd.add(std);
            Register(_listofstd.get(_listofstd.size()-1),_modules.get(indexmodule));
        }else if(qtype.equals("details")){
            String name = sc.next();
            int indexstd = 0;
            for (int i = 0; i < _listofstd.size(); i++){
                if (_listofstd.get(i).getName().equals(name)){
                    indexstd = i;
                    Details(_listofstd.get(indexstd));
                    return;
                }
            }
            System.out.println(name+ " has no modules");
        }else if(qtype.equals("remove")){
            String name = sc.next();
            String module = sc.next();
            Student temp = new Student(name,0,new ArrayList<Module>());
            int indexmodule = 0;
            for (int y = 0; y < _modules.size(); y++){
                if (_modules.get(y).getCode().equals(module)){
                    indexmodule = y;
                }
            }
            int indexstd = 0;
            for (int x = 0; x < _listofstd.size(); x++){
                if (_listofstd.get(x).getName().equals(name)){
                    indexstd = x;
                }
            }
            for (int i = 0; i < _listofstd.size(); i++){
                if (_listofstd.get(i).getName().equals(name)){
                    Remove(_listofstd.get(i),_modules.get(indexmodule));
                    return;
                }
            }
            Student std = new Student(name,0,new ArrayList<Module>());
            _listofstd.add(std);
            Remove(_listofstd.get(_listofstd.size()-1),_modules.get(indexmodule));
        }else if(qtype.equals("highest")){
            Highest(_listofstd);
        }
    }

    public static void main(String[] args) {
        Ivle ivle = new Ivle();
        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt();
        int Q = sc.nextInt();
        ArrayList<Module> _modules = new ArrayList<Module>();
        ArrayList<Student> _listofstd = new ArrayList<Student>();
        for (int n = 0; n < N; n++){
            Module mdl = new
                Module(sc.next(),sc.nextInt(),sc.nextInt(),new
                        ArrayList<Student>());
            _modules.add(mdl);
        }
        for (int q = 0; q < Q; q++){
            ivle.run(sc,_modules, _listofstd);
        }
    }
}

class Module {
    // define your own attributes, constructor, and methods here
    private String _code;
    private int _mc;
    private int _vacancy;
    private ArrayList<Student> _student;

    public Module(String code, int mc, int vacancy, ArrayList<Student>
            student){
        _code = code;
        _mc = mc;
        _vacancy = vacancy;
        _student = student;
    }

    public String getCode(){return _code;}
    public int getmc(){return _mc;}
    public int getVacancy(){return _vacancy;}
    public ArrayList<Student> getStudent(){return _student;}
    public void deductvac(){_vacancy -= 1;}
    public void addvac(){_vacancy += 1;}
}

class Student {
    // define your own attributes, constructor, and methods here
    private int _mc;
    private String _name;
    private ArrayList<Module> _modules;
    public Student(String name, int mc, ArrayList<Module> modules){
        _name = name;
        _mc = mc;
        _modules = modules;
    }

    public int getmc(){return _mc;}
    public String getName(){return _name;}
    public ArrayList<Module> getModules(){return _modules;}
    public void addmc(int x){_mc += x;}
    public void deductmc(int y){_mc -= y;}
}
