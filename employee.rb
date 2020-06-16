class Employee
  attr_accessor :id, :name, :manager

  def initialize(id, name, manager)
    @id = id
    @name = name
    @manager = manager
  end
  
end
