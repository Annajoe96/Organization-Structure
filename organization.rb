require './employee.rb'

class Organization

  def initialize
    # directory of employees with O(1) lookup with key
    @employees = {}

    # org chart of who manages who
    @managers = {}
  end

  def add_employee(id, name, manager)
    @employees[id] = Employee.new(id, name, manager)

    @managers[id] = []
    
    if @managers[manager]
      @managers[manager] << id
    else
      @managers[manager] = [id]
    end
  end

  def return_managers
    @managers
  end

  def change_manager(id, manager)
    return if @employees[id] == nil || @employees[manager] == nil

    @managers[@employees[id].manager].delete(id)
    @employees[id].manager = manager
    @managers[manager] << id
  end


  def remove_employee(id)
    employee = @employees[id]
    return if employee == nil

    # if they manager others
    if @managers[id].size > 0
      new_manager = employee.manager
      @managers[new_manager] += @managers[id]

      # assign new manager
      @managers[id].each do |i|
        @employees[i].manager = new_manager
      end
    end

    # remove from being managed by someone else
    @managers[employee.manager].delete(id)

    # remove being a manager
    @managers.delete(id)

    # remove employee from records
    @employees.delete(id)
  end


  def supervisor_team_count(id)
    return 0 if @managers[id] == nil

    @managers[id].size + @managers[id].map{|e| supervisor_team_count(e)}.sum
  end

  
  #printing each manager team from manager hash
  def print_org
    seen = {}

    @managers.each do |key, value|
      next if key == -1
      manager_list(key, seen, 0)
    end

  end

  def manager_list(id, seen, level)
    
    if seen[id]
      return
    end

    print_employee(id, level)
    seen[id] = true
  
    #recursively figuring out the management level of employee
    @managers[id].each do |m|
      manager_list(m, seen, level + 1)
    end

  end

  def print_employee(id, level)
    employee = @employees[id]
    
    #incrementing space according to level
    result = ""
    if level > 0
      result += "   " * level
    end

    result += "#{employee.name} [#{employee.id}]"
    puts result

  end


end



