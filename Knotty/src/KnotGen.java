import java.awt.FlowLayout;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;

public class KnotGen {

	private static int[][][] blocks = {
		{		//0
			{0,0,0,0,0},{0,0,0,0,0},{0,0,0,0,0},{0,0,0,0,0},{0,0,0,0,0}
		}, {	//1
			{0,0,0,0,0},{0,0,0,0,0},{1,1,1,1,1},{0,0,0,0,0},{0,0,0,0,0}
		}, {	//2
			{0,0,1,0,0},{0,0,1,0,0},{0,0,1,0,0},{0,0,1,0,0},{0,0,1,0,0}
		}, {	//3
			{0,0,0,0,0},{0,0,0,0,0},{1,1,1,0,0},{0,0,1,0,0},{0,0,1,0,0}
		}, {	//4
			{0,0,0,0,0},{0,0,0,0,0},{1,1,1,1,1},{0,0,0,0,0},{0,0,0,0,0}
		}, {	//5
			{0,0,0,0,0},{0,0,0,0,0},{1,1,1,1,1},{0,0,0,0,0},{0,0,0,0,0}
		}, {	//6
			{0,0,0,0,0},{0,0,0,0,0},{0,0,1,1,1},{0,0,1,0,0},{0,0,1,0,0}
		}, {	//7 (unused)
			{0,0,0,0,0},{0,0,0,0,0},{0,0,0,0,0},{0,0,0,0,0},{0,0,0,0,0}
		}, {	//8
			{0,0,1,0,0},{0,0,1,0,0},{0,0,1,0,0},{0,0,1,0,0},{0,0,1,0,0}
		}, {	//9
			{0,0,1,0,0},{0,0,1,0,0},{1,1,1,0,0},{0,0,0,0,0},{0,0,0,0,0}
		}, {	//10 or A
			{0,0,1,0,0},{0,0,1,0,0},{0,0,1,0,0},{0,0,1,0,0},{0,0,1,0,0}
		}, {	//11 or B(unused)
			{0,0,0,0,0},{0,0,0,0,0},{0,0,0,0,0},{0,0,0,0,0},{0,0,0,0,0}
		}, {	//12 or C
			{0,0,1,0,0},{0,0,1,0,0},{0,0,1,1,1},{0,0,0,0,0},{0,0,0,0,0}
		}, {	//13 or D (used to represent vertical overlap)
			{0,0,1,0,0},{0,0,1,0,0},{1,0,1,0,1},{0,0,1,0,0},{0,0,1,0,0}
		}, {	//14 or E (used to represent horizontal overlap)
			{0,0,1,0,0},{0,0,0,0,0},{1,1,1,1,1},{0,0,0,0,0},{0,0,1,0,0}
		}, {	//15 or F (unused except for debugging)
			{0,0,1,0,0},{0,0,1,0,0},{1,1,1,1,1},{0,0,1,0,0},{0,0,1,0,0}
		}
	};
	private static int[][][] blocks2 = {
			{		//0
				{0,0,0,0,0,0,0},{0,0,0,0,0,0,0},{0,0,0,0,0,0,0},{0,0,0,0,0,0,0},{0,0,0,0,0,0,0},{0,0,0,0,0,0,0},{0,0,0,0,0,0,0}
			}, {	//1
				{0,0,0,0,0,0,0},
				{0,0,0,0,0,0,0},
				{1,1,1,1,1,1,1},
				{0,0,0,0,0,0,0},
				{1,1,1,1,1,1,1},
				{0,0,0,0,0,0,0},
				{0,0,0,0,0,0,0}
			}, {	//2
				{0,0,1,0,1,0,0},
				{0,0,1,0,1,0,0},
				{0,0,1,0,1,0,0},
				{0,0,1,0,1,0,0},
				{0,0,1,0,1,0,0},
				{0,0,1,0,1,0,0},
				{0,0,1,0,1,0,0}
			}, {	//3
				{0,0,0,0,0,0,0},
				{0,0,0,0,0,0,0},
				{1,1,1,1,1,0,0},
				{0,0,0,0,1,0,0},
				{1,1,1,0,1,0,0},
				{0,0,1,0,1,0,0},
				{0,0,1,0,1,0,0}
			}, {	//4
				{0,0,0,0,0,0,0},
				{0,0,0,0,0,0,0},
				{1,1,1,1,1,1,1},
				{0,0,0,0,0,0,0},
				{1,1,1,1,1,1,1},
				{0,0,0,0,0,0,0},
				{0,0,0,0,0,0,0}
			}, {	//5
				{0,0,0,0,0,0,0},
				{0,0,0,0,0,0,0},
				{1,1,1,1,1,1,1},
				{0,0,0,0,0,0,0},
				{1,1,1,1,1,1,1},
				{0,0,0,0,0,0,0},
				{0,0,0,0,0,0,0}
			}, {	//6
				{0,0,0,0,0,0,0},
				{0,0,0,0,0,0,0},
				{0,0,1,1,1,1,1},
				{0,0,1,0,0,0,0},
				{0,0,1,0,1,1,1},
				{0,0,1,0,1,0,0},
				{0,0,1,0,1,0,0}
			}, {	//7 (unused)
				{0,0,0,0,0,0,0},
				{0,0,0,0,0,0,0},
				{0,0,0,0,0,0,0},
				{0,0,0,0,0,0,0},
				{0,0,0,0,0,0,0},
				{0,0,0,0,0,0,0},
				{0,0,0,0,0,0,0}
			}, {	//8
				{0,0,1,0,1,0,0},
				{0,0,1,0,1,0,0},
				{0,0,1,0,1,0,0},
				{0,0,1,0,1,0,0},
				{0,0,1,0,1,0,0},
				{0,0,1,0,1,0,0},
				{0,0,1,0,1,0,0}
			}, {	//9
				{0,0,1,0,1,0,0},
				{0,0,1,0,1,0,0},
				{1,1,1,0,1,0,0},
				{0,0,0,0,1,0,0},
				{1,1,1,1,1,0,0},
				{0,0,0,0,0,0,0},
				{0,0,0,0,0,0,0}
			}, {	//10 or A
				{0,0,1,0,1,0,0},
				{0,0,1,0,1,0,0},
				{0,0,1,0,1,0,0},
				{0,0,1,0,1,0,0},
				{0,0,1,0,1,0,0},
				{0,0,1,0,1,0,0},
				{0,0,1,0,1,0,0}
			}, {	//11 or B(unused)
				{0,0,0,0,0,0,0},
				{0,0,0,0,0,0,0},
				{0,0,0,0,0,0,0},
				{0,0,0,0,0,0,0},
				{0,0,0,0,0,0,0},
				{0,0,0,0,0,0,0},
				{0,0,0,0,0,0,0}
			}, {	//12 or C
				{0,0,1,0,1,0,0},
				{0,0,1,0,1,0,0},
				{0,0,1,0,1,1,1},
				{0,0,1,0,0,0,0},
				{0,0,1,1,1,1,1},
				{0,0,0,0,0,0,0},
				{0,0,0,0,0,0,0}
			}, {	//13 or D (used to represent vertical overlap)
				{0,0,1,0,1,0,0},
				{0,0,1,0,1,0,0},
				{1,0,1,0,1,0,1},
				{1,0,1,0,1,0,1},
				{1,0,1,0,1,0,1},
				{0,0,1,0,1,0,0},
				{0,0,1,0,1,0,0}
			}, {	//14 or E (used to represent horizontal overlap)
				{0,0,1,1,1,0,0},
				{0,0,0,0,0,0,0},
				{1,1,1,1,1,1,1},
				{0,0,0,0,0,0,0},
				{1,1,1,1,1,1,1},
				{0,0,0,0,0,0,0},
				{0,0,1,1,1,0,0}
			}, {	//15 or F (unused except for debugging)
				{0,0,1,0,1,0,0},
				{0,0,1,0,1,0,0},
				{1,1,1,1,1,1,1},
				{0,0,1,0,1,0,0},
				{1,1,1,1,1,1,1},
				{0,0,1,0,1,0,0},
				{0,0,1,0,1,0,0}
			}
		};

	
	public static void main(String[] args) {
		int size = 10;
		int mult = 1;
		long seed = 11111111L;
		
		if(args.length == 0) {
			System.out.println("Using hardcoded values.");
		} else if(args.length == 3) {
			System.out.println("Using arguemnts for input.");
			size = Integer.valueOf(args[0]);
			mult = Integer.valueOf(args[1]);
			seed = Long.valueOf(args[2]);
		} else {
			System.out.println("Usage: Knotty [size spacing seed]");
		}
		
		BufferedImage img = knotGen( size, mult, seed, true );
		
		String filename = "Knot";
		filename = filename + seed + "_s" + size + "_m" + mult;
		filename = filename + ".jpg";
		
		//display your creation!
		ImageIcon icon=new ImageIcon(img);
		JFrame frame=new JFrame();
		frame.setLayout(new FlowLayout());
		int squareDim = blocks2[0].length*((size+1)*mult)*2;
		frame.setSize(squareDim+30,squareDim+50);
		JLabel lbl=new JLabel();
		lbl.setIcon(icon);
		frame.setTitle( filename );
		frame.add(lbl);
		frame.setVisible(true);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

	/**
	 * Generates a knot that resembles a celtic knot based on the input parameters
	 * 
	 * @param	size	could be thought of as the complexity of the knot
	 * @param	mult	the scale of the image (smaller = denser)
	 * @param	seed	the seed for the RNG used to generate the knot
	 * @param	save	true if you want the image to be saved to the pictures folder
	 * @return			the image file of the generated know
	 */
	public static BufferedImage knotGen( int size, int mult, long seed, boolean save ) {
		//make an array and shuffle it
		int[] a = new int[size];
		for( int i = 1; i <= a.length; i++ ) {
			a[i-1] = i*mult;
		}
		Shuffler s = null;
		try {
			s = new Shuffler( seed );
		} catch (Exception e) { System.err.println( e.getMessage() ); }
		s.shuffle(a);
		
		//set up the coordinates from both ends up to the mirror point
		int[][] pts = calculatePts( a );
		//so now we have the points of each corner of the knot.
		//need to create the 2 dimensional plotting of it, point by point
		int[][] plot = new int[(size+1)*mult][(size+1)*mult];
		calculatePlot( plot, pts );
		//calculate the under/over pattern
		calculateOverlaps( plot, pts );
		
		//now make it into an image file...
		BufferedImage img = generateImg( plot );
		if( save ) {
			String filename = "Knot";
			filename = filename + seed + "_s" + size + "_m" + mult;
			filename = filename + ".jpg";
			System.out.println( "Saving Image as \"" + System.getProperty("user.dir") + "\\samples\\" + filename + "\"" );  //DEBUG
			File saveMe = new File( System.getProperty("user.dir") + "\\samples\\"+ filename );
			try {
				ImageIO.write( img, "jpg", saveMe );
			} catch (IOException e) {
				//e.printStackTrace();
				System.err.println( "FAILED TO WRITE IMAGE" );
			}
		}
		return img;
	}
	
	/**
	 * Construct an image of the knot based on the plot of it
	 * 
	 * @param	plot	the 2D storage of the knot as corners, lines, and intersections 
	 * @return			the image file of the full knot
	 */
	private static BufferedImage generateImg(  int[][] plot ) {
		int blockSize = blocks2[0].length;
		int[][] bwArray = new int[plot.length*blockSize][plot.length*blockSize];
		for( int y = plot.length-1; y >= 0; y-- ) {
			for( int x = 0; x < plot[y].length; x++ ) {
	//			printGrid(bwArray);
				transferBlock( blocks2[plot[y][x]], bwArray, y*blockSize, x*blockSize);
			}
		}
//		printGrid(bwArray);
		
		
		BufferedImage img = new BufferedImage(bwArray.length*2, bwArray.length*2, BufferedImage.TYPE_INT_RGB);
		int size = (bwArray.length*2)-1;
		for( int y = 0; y < bwArray.length; y++) {
			for( int x = 0; x < bwArray[y].length; x++ ) {
				int pixelVal = 0xFFFFFF*((1+bwArray[(bwArray.length-1)-x][(bwArray.length-1)-y])%2);
				img.setRGB(x, y, pixelVal );
				img.setRGB(size - x, y, pixelVal );
				img.setRGB(x, size - y, pixelVal );
				img.setRGB(size - x, size - y, pixelVal );
			}
		}
		
		return img;
	}
	
	/**
	 * Prints the basic outline of the knot, used for debugging
	 * 
	 * @param	grid	the knot stored in a 2D form
	 */
	private static void printGrid( int[][] grid ) {
		System.out.println("-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-");
		for( int a = grid.length-1; a >= 0; a--) {
			for( int b = 0; b < grid[a].length; b++ ) {
				if( grid[a][b] != 0 ) {
					System.out.print("#");
				} else {
					System.out.print(" ");
				}
			}
			System.out.println();
		}
		System.out.println("-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-");
	}
	
	/**
	 * Add the given block of black or white values into the 2D grid of the knot
	 * 
	 * @param	block	the block to transfer
	 * @param	grid	the 2D grid representation of the knot
	 * @param	y		the bottom coordinate of where the block will go in the grid
	 * @param	x		the left coordinate of where the block will go in the grid
	 */
	private static void transferBlock( int[][] block, int[][] grid, int y, int x ) {
		for( int a = 0; a < block.length; a++ ) {
			for( int b = 0; b < block[a].length; b++ ) {
				grid[y+a][x+b] = block[(block.length-1)-a][b];
			}
		}
	}
	
	/**
	 * make the array of coordinates of the corners of the knot on the coordinate plane
	 * 
	 * @param	a	the randomly shuffled numbers used to generate the points
	 * @return		the array of (x,y) points
	 */
	private static int[][] calculatePts( int[] a ) {
		int size = a.length;
		int len = (2*size)+1;
		int[][] pts = new int[len][2];
		boolean isX = true;
		len--; //to index the last element
		pts[0][1] = 0;
		pts[len][0] = 0;
		for( int i = 0; i < size; i++ ) {
			if( isX ) {
				pts[i][0] = a[i];
				pts[i+1][0] = a[i];
				//mirror
				pts[len-i][1] = a[i];
				pts[(len-i)-1][1] = a[i];
			} else {
				pts[i][1] = a[i];
				pts[i+1][1] = a[i];
				//mirror
				pts[len-i][0] = a[i];
				pts[(len-i)-1][0] = a[i];
			}
			isX = !isX;
		}
		//set up the mirror point
		pts[size][0] = a[size-1];
		pts[size][1] = a[size-1];
		return pts;
	}
	
	/**
	 * generate the 2D plot of the points and the lines between them
	 * 
	 * @param	plot	the array to store the plot
	 * @param	pts		the array of points to plot
	 */
	private static void calculatePlot( int[][] plot, int[][] pts ) {
		for( int i= 0; i < pts.length-1; i++ ) {
			int delta = 1;  //direction of line
			int xy = 0;	//which will be changing
			if( pts[i][0] == pts[i+1][0] ) {
				//vertical line
				xy = 1;
				if( pts[i][1] > pts[i+1][1]) { 
					delta = -1;
					plot[pts[i][1]][pts[i][0]] = plot[pts[i][1]][pts[i][0]] | 2;
					plot[pts[i+1][1]][pts[i+1][0]] = plot[pts[i+1][1]][pts[i+1][0]] | 8;
				} else {
					delta = 1;
					plot[pts[i][1]][pts[i][0]] = plot[pts[i][1]][pts[i][0]] | 8;
					plot[pts[i+1][1]][pts[i+1][0]] = plot[pts[i+1][1]][pts[i+1][0]] | 2;
				}
			} else {
				//horizontal line
				xy = 0;
				if( pts[i][0] > pts[i+1][0] ) {
					delta = -1;
					plot[pts[i][1]][pts[i][0]] = plot[pts[i][1]][pts[i][0]] | 1;	//0001
					plot[pts[i+1][1]][pts[i+1][0]] = plot[pts[i+1][1]][pts[i+1][0]] | 4;	//0100
				} else {
					delta = 1;
					plot[pts[i][1]][pts[i][0]] = plot[pts[i][1]][pts[i][0]] | 4;	//0100
					plot[pts[i+1][1]][pts[i+1][0]] = plot[pts[i+1][1]][pts[i+1][0]] | 1;	//0001
				}
			}
			for( int n = pts[i][xy] + delta; n != pts[i+1][xy]; n=n+delta ) {
				if( xy == 0 ) {	//vertical
					plot[pts[i][(xy+1)%2]][n] = plot[pts[i][(xy+1)%2]][n] | 5;	// 1010
				} else {	//horizontal
					plot[n][pts[i][(xy+1)%2]] = plot[n][pts[i][(xy+1)%2]] | 10;	// 0101
				}
			}
		}
		plot[pts[pts.length-1][0]][pts[pts.length-1][1]] = 8;
		
	}
	
	/**
	 * Trace through the plot and update it to include the necessary overlaps
	 * Keeps track of whether the overlap has the vertical or horizontal line on top
	 * 
	 * @param	plot	The 2D plot of the knot
	 * @param 	pts		The array of points for the corners of the knot
	 */
	private static void calculateOverlaps( int[][] plot, int[][] pts ) {
		boolean over = true;
		int delta = 1;  //direction of line
		int xy = 0;	//which will be changing
		for( int i = 0; i < pts.length-1; i++ ) {
			if( pts[i][0] == pts[i+1][0] ) {
				//vertical line
				xy = 1;
				if( pts[i][1] > pts[i+1][1]) { 
					delta = -1;
				} else {
					delta = 1;
				}
			} else {
				//horizontal line
				xy = 0;
				if( pts[i][0] > pts[i+1][0] ) {
					delta = -1;
				} else {
					delta = 1;
				}
			}
			for( int n = pts[i][xy] + delta; n != pts[i+1][xy]; n=n+delta ) {
				if( plot[pts[i][(xy+1)%2]][n] == 15 ) {
					//found an un-calculated overlap!
					if( xy == 1 ) {
						//the current line is vertical
						if( over ) {
							plot[pts[i][(xy+1)%2]][n] = 13;
						} else {
							plot[pts[i][(xy+1)%2]][n] = 14;
						}
					} else {
						//the current line is horizontal
						if( over ) {
							plot[pts[i][(xy+1)%2]][n] = 14;
						} else {
							plot[pts[i][(xy+1)%2]][n] = 13;
						}
					}
					over = !over;
				}
			}
		}
	}
	
	/**
	 * prints an ASCII version of the knot
	 * 
	 * @param	plot	The 2D plot of the knot
	 */
	private static void fancyPrint( int[][] plot ) {
		System.out.println();
		for( int a = plot.length-1; a >=0; a-- ) {
			for( int b = 0; b < plot[a].length; b++ ) {
				switch( plot[a][b] ) {
				case 0:
					System.out.print("  ");
					break;
				case 1:
					System.out.print("- ");
					break;
				case 2:
					System.out.print(" |");
					break;
				case 3:
					System.out.print("-o");
					break;
				case 4:
					System.out.print(" -");
					break;
				case 5:
					System.out.print("--");
					break;
				case 6:
					System.out.print(" o");
					break;
				case 8:
					System.out.print(" |");
					break;
				case 9:
					System.out.print("-o");
					break;
				case 10:
					System.out.print(" |");
					break;
				case 12:
					System.out.print(" o");
					break;
				case 13:
					//vertical overlap
					System.out.print("-|");
					break;
				case 14:
					//horizontal overlap
					System.out.print("--");
					break;
				case 15:
					//overlap (should only happen in pre-overlap version)
					System.out.print("-+");
					break;
				default:
					//should never happen...
					System.out.print("  ");
					break;
				}
			}
			System.out.println();
		}
		
		System.out.println();
	}
	
	/**
	 * Prints the actual values stored in the plot for debugging purposes
	 * 
	 * @param	plot	the 2D plot of the knot
	 */
	private static void debugPrint( int[][] plot ) {
		//debug print, yo!
		System.out.println();
		for( int y = plot.length-1; y >=0; y-- ) {
			if( y < 10 ) {
				System.out.print( y+" " );
			} else {
				System.out.print(y);
			}
			for( int x = 0; x < plot[y].length; x++ ) {
				if( plot[y][x] != 0 ) {String p = Integer.toHexString(plot[y][x]).toUpperCase();
					System.out.print(p+"|");
				} else {
					System.out.print(" |");
				}
			}
			System.out.println();
		}
		System.out.print("  ");
		for( int i = 0; i < plot[0].length; i++ ) {
			if( i < 10 ) {
				System.out.print( i +" ");
			} else {
				System.out.print(i);
			}
		}
		System.out.println();
	}

	/**
	 * prints the list of points in a (x,y) format
	 * 
	 * @param	pts	the array of point coordinate values
	 */
	private static void printPts( int[][] pts ) {
		for( int i = 0; i < pts.length; i++ ) {
			System.out.print("("+ pts[i][0] +","+ pts[i][1] +")");
		}
		System.out.println();
	}
	
}
