`timescale 1ns / 1ps


module func_tb;

    reg clk = 0;
    reg rst = 0;
    reg [7:0] a;
    reg [7:0] b;
    reg start;

    wire busy;
    wire [11:0] y;
    
    func func(
        .clk_i(clk),
        .rst_i(rst),
        
        .a_bi(a),
        .b_bi(b),
        .start_i(start),
        
        .busy_o(busy),
        .y_bo(y)
    );

    task test_func;
        input [3:0] test_num;
        input [7:0] test_a;
        input [7:0] test_b;
        input [11:0] test_res;
        begin
            a = test_a;
            b = test_b;
            start = 1;
            #10
            start = 0;
            while (busy) begin
                #5;
            end
            if(y == test_res) $display("Test %d: Correct", test_num);
            else $display("Test %d: Failed %d", test_num, y);
        end
    endtask

    initial begin
        test_func(0, 0, 0, 0);
        test_func(1, 1, 0, 0);
        test_func(2, 1, 1, 1);
        test_func(3, 2, 4, 4);
        test_func(4, 2, 9, 6);
        test_func(5, 78, 86, 702);
        test_func(6, 178, 230, 2670);
        test_func(7, 199, 196, 2786);
        test_func(8, 196, 199, 2744);
        test_func(9, 255, 255, 3825);

        
    end   
    
    always begin
        #5  clk =  ! clk;
    end    
endmodule
